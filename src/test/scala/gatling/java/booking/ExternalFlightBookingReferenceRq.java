package gatling.java.booking;

import com.openjaw.api.model.ExternalBookingPurchaseRequest;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;
import com.openjaw.api.model.ExternalFlightBookingReference;

public class ExternalFlightBookingReferenceRq extends RequestBase {

    public String getBody(String reservationId,String resultSetId){

        ExternalFlightBookingReference reference = new ExternalFlightBookingReference();
        ExternalBookingPurchaseRequest rq = new ExternalBookingPurchaseRequest();
        rq.setReservationId(reservationId);
        rq.setResultSetId(resultSetId);
        reference.setPurchaseRequest(rq);
        return Tools.objectTOString(reference);
    }

    public String getUrl(){
        return "/external/bookings/flights/import";
    }
}
