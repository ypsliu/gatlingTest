package gatling.java.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;


public abstract class RequestBase {
    //接口文件重写
    public String getBody(){return "";}

    //接口文件实现
    public String getUrl(){return "";};//{return this.requestJsonFile.get("path").toString();}

    //接口文件重写
    public Map<String,String> getHeader() {
        Map<String, String> a = new HashMap<>();
        return a;
    }
    //接口文件重写
    public Map<String, String> getHeader(String token) {
        Map<String, String> a = new HashMap<>();
        a.put("User-Token",token);
        return a;
    }

//    //接口文件实现
//    public abstract String checkAndSave(String response);

}

