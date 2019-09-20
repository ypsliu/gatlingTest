package gatling.java.Ezpay;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;
import com.openjaw.api.model.ConfirmPaymentInput;

import java.util.Map;

public class ConfirmRedirectRq extends RequestBase {
    @Override
    public String getUrl() {
        return "/ezpay/confirmRedirect";
    }

    public String getBody(String bookingId, Map<String, String> ezpayDetailMap ){
        ConfirmPaymentInput confirmPaymentInput = new ConfirmPaymentInput();
        confirmPaymentInput.setApptype(ezpayDetailMap.get("apptype"));
        confirmPaymentInput.setBankid(ezpayDetailMap.get("bankid"));
        confirmPaymentInput.setBillno(ezpayDetailMap.get("billno"));
        confirmPaymentInput.setBookingId(bookingId);
        confirmPaymentInput.setLan(ezpayDetailMap.get("lan"));
        confirmPaymentInput.setMsg(ezpayDetailMap.get("msg"));
        confirmPaymentInput.setOrdercurtype(ezpayDetailMap.get("ordercurtype"));
        confirmPaymentInput.setOrderno(ezpayDetailMap.get("orderno"));
        confirmPaymentInput.setOrdertype(ezpayDetailMap.get("ordertype"));
        confirmPaymentInput.setOrgid(ezpayDetailMap.get("orgid"));
        confirmPaymentInput.setPayamount(ezpayDetailMap.get("payamount"));
        confirmPaymentInput.setPaydate(ezpayDetailMap.get("paydate"));
        confirmPaymentInput.setPaystatus(ezpayDetailMap.get("paystatus"));
        confirmPaymentInput.setPaytime(ezpayDetailMap.get("paytime"));
        confirmPaymentInput.setReturntype(ezpayDetailMap.get("returntype"));
        confirmPaymentInput.setSignature(ezpayDetailMap.get("signature"));

        return Tools.objectTOString(confirmPaymentInput);
    }
}