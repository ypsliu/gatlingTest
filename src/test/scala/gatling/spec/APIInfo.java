package gatling.spec;

import org.springframework.web.bind.annotation.RequestMethod;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import gatling.spec.Common;

public class APIInfo {
    public static Map<String, Object> getApiInfo(String Module ,String requestName){
        Map<String, Object> info = new HashMap<>();
        Class c = null;
        String tag = "";
        for (String s:Module.split(" ")) {
            tag = tag + Common.toUpperCaseFirstOne(s);
        }

        try {
            c = Class.forName("com.openjaw.api.controller."+ Common.toUpperCaseFirstOne(tag) + "Api");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(Method m :c.getDeclaredMethods())
        {
            if (m.getName() == requestName)
            {
                RequestMethod[] t = (RequestMethod[]) getAnnotationValue(m.getAnnotations()[2],"method");
                info.put("type",t[0]);
                String[] a = (String[]) getAnnotationValue(m.getAnnotations()[2],"value");
                info.put("url",a[0]);
                String b = "";
                for (Parameter p:m.getParameters()) {
                    String ps = p.getType().toString().substring(6);
                    System.out.println(ps);
                    if (!ps.equals("java.lang.String")) {
                        //info.put("body", p.getType());
                        b = ps;
                        //break;
                    }
                }
                info.put("bodyClass", b);
                break;
            }
        }
        System.out.println(info);
        return info;
    }

    public static Object getAnnotationValue(Annotation annotation, String property) {
        Object result = null;
        if (annotation != null) {
            InvocationHandler invo = Proxy.getInvocationHandler(annotation); //获取被代理的对象
            Map map = (Map) getFieldValue(invo, "memberValues");
            if (map != null) {
                result = map.get(property);
            }
        }
        return result;
    }

    public static <T> Object getFieldValue(T object, String property) {
        if (object != null && property != null) {
            Class<T> currClass = (Class<T>) object.getClass();

            try {
                Field field = currClass.getDeclaredField(property);
                field.setAccessible(true);
                return field.get(object);
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException(currClass + " has no property: " + property);
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args){
        getApiInfo("car","createCarSearch");
    }

}


