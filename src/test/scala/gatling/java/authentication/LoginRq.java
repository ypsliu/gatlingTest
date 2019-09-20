package gatling.java.authentication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import gatling.java.common.RequestBase;
import com.openjaw.api.model.Login;
import com.openjaw.api.model.LoginType;
import static gatling.java.common.Tools.objectTOString;

public class LoginRq extends RequestBase {

    public String getBody(String loginType,String user,String password){
        Login login = new Login();
        login.setLoginType(LoginType.fromValue(loginType));
        login.setPassword(password);
        login.setUser(user);
        return objectTOString(login);
    }

    public String getUrl(){
        return "/authentication";
    }

    public String checkAndSave(String response){
        JSONObject rp = JSON.parseObject(response);
        rp.get("token");
        return "0";
    }


}
