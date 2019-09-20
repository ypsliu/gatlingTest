package gatling.java.bookingProduct;

import com.openjaw.api.model.CustomerSpecialRequest;
import com.openjaw.api.model.SpecialRequestsReference;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class AddSpecialRequestsRq extends RequestBase {
    public String getUrl(String bookingId,String mealProductId) {
        return "/bookings/"+bookingId+"/products/"+mealProductId+"/flight/specialRequests";
    }

    public String getBody(String mealProductId,String freemealProductId){
        CustomerSpecialRequest customerSpecialRequest = new CustomerSpecialRequest();
        customerSpecialRequest.customerId(1).resultId(freemealProductId);

        SpecialRequestsReference specialRequestsReference = new SpecialRequestsReference();
        specialRequestsReference.setResultSetId(mealProductId);
        specialRequestsReference.addSpecialServiceRequestsItem(customerSpecialRequest);

        return Tools.objectTOString(specialRequestsReference);
    }
}