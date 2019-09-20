package gatling.java.authentication;

import com.openjaw.api.model.ContactMobile;
import gatling.java.common.RequestBase;

import static gatling.java.common.Tools.objectTOString;

public class ContactMobileRq extends RequestBase {

    public String getBody(String authenticationType,String mobile){
        ContactMobile cmobile = new ContactMobile();
        cmobile.setAuthenticationType(ContactMobile.AuthenticationTypeEnum.valueOf(authenticationType));
        cmobile.setMobile(mobile);
        return objectTOString(cmobile);
    }

    public String getUrl(){
        return "/mfa/mobile";
    }

}
