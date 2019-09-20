package gatling.java.Ezpay;

import gatling.java.common.RequestBase;
import gatling.java.common.Tools;
import com.openjaw.api.model.RedirectPaymentInput;

public class AuthoriseRedirectRq extends RequestBase {
    @Override
    public String getUrl() {
        return "/ezpay/authoriseRedirect";
    }

    public String getBody(String bookingId){
        RedirectPaymentInput redirectPaymentInput = new RedirectPaymentInput();
//        redirectPaymentInput.setAddress("辽宁省大连市腾飞47600号");
        redirectPaymentInput.setBankId("EASYPAY");
        redirectPaymentInput.setBookingId(bookingId);
//        redirectPaymentInput.setCity("dalian");
//        redirectPaymentInput.setDeliveryOption(3);
//        redirectPaymentInput.setGender("Mr");
//        redirectPaymentInput.setProvince("liaoning");
//        redirectPaymentInput.setRemark("string");
//        redirectPaymentInput.setSurname("dubolinguest10");
//        redirectPaymentInput.setTelephone("13655555555");
//        redirectPaymentInput.setZip("10000");

        return Tools.objectTOString(redirectPaymentInput);
    }
}
