package gatling.java.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
//import scala.reflect.internal.util.FileUtils;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Tools {
    public static String getTodayDate() {
        String s = java.time.LocalDate.now().toString();
        return s;
    }

    public static LocalDate getLocalDate(int offset) {
        return java.time.LocalDate.parse(getDate(offset));
    }

    public static LocalDate getLastLocalDate(int offset) {
        return java.time.LocalDate.parse(getLastDate(offset));
    }

    public static String getDate(int day) {
        Date d = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
        return format0.format(now.getTime());
    }

    public static String getLastDate(int day) {
        Date d = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
        return format0.format(now.getTime());
    }

    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static JSONObject mapToJsonObj(Map<String, String> map) {
        JSONObject resultJson = new JSONObject();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            resultJson.put(key, map.get(key));
        }
        return resultJson;
    }

    public static String objectTOString(Object o) {
        return JSON.toJSONString(o);
    }

    public static JSONObject getFromJsonFile(String jsonFile) {
        File file = new File(jsonFile);
        String jsonString = null;
        try {
            jsonString = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = JSONObject.parseObject(jsonString);
        return obj;
    }

    public static String getClassName() {
        String fullName = Thread.currentThread().getStackTrace()[2].getClassName();
        return fullName.split(".")[-1];
    }

    public static LocalDate getLastLocalDateByStr(String datestr) {
        return java.time.LocalDate.parse(datestr);
    }

    public static String emptyStrTranZero(String number){
        return StringUtils.isEmpty(number)?"0":number;
    }

    public static void main(String[] args) {
        //System.out.println(readConfigValue("Accept-Language"));
        System.out.println(getTodayDate());
        System.out.println(toUpperCaseFirstOne("creass"));

        Map m = new HashMap<String, String>();
        m.put("name", "test");
        m.put("age", "20");

        System.out.println(mapToJsonObj(m));
        System.out.println();
    }

}
