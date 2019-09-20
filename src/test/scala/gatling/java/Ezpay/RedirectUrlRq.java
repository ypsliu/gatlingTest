package gatling.java.Ezpay;

import gatling.java.common.RequestBase;

import java.util.HashMap;
import java.util.Map;

public class RedirectUrlRq extends RequestBase {
    public String getUrl(String redirectUrl) {
        return redirectUrl;
    }

    public Map<String, String> getHeader() {
        Map<String, String> a = new HashMap<>();
        a.put("Referer","http://119.254.234.92:9280/easypay/");
        //a.put("Content-Type","application/json");
        return a;
    }
}
