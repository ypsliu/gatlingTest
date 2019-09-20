package gatling.java.Ezpay;

import gatling.java.common.RequestBase;


public class Get_detailRq extends RequestBase {
    public String getUrl(String easyPayResPageURL) {
        return easyPayResPageURL;
    }

}
