package gatling.java.bookingProduct;

import gatling.java.common.RequestBase;
import gatling.java.common.Tools;
import com.openjaw.api.model.FlightProductReference;
public class AddFlightProductRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/"+bookingId+"/products/flight";
    }

    public String getBody(String priceId, String resultId, String resultSetId){
        FlightProductReference flightProductReference = new FlightProductReference();
        flightProductReference.setPriceId(priceId);
        flightProductReference.setResultId(resultId);
        flightProductReference.setResultSetId(resultSetId);
        return Tools.objectTOString(flightProductReference);
    }
}