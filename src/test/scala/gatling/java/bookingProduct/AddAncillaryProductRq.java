package gatling.java.bookingProduct;

import com.openjaw.api.model.AncillaryProductReference;
import com.openjaw.api.model.AncillaryProductReferences;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class AddAncillaryProductRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/"+bookingId+"/products/ancillary";
    }

    public String getBody(String mealResultId,String chargeablemealProductId){
        AncillaryProductReferences aprs = new AncillaryProductReferences();
        aprs.resultSetId(mealResultId);
        AncillaryProductReference apr = new AncillaryProductReference()
                .customerId(1).priceId(0).quantity(1).resultId(chargeablemealProductId);
        aprs.addAncillariesItem(apr);

        return Tools.objectTOString(aprs);
    }
}