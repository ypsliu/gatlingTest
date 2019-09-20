package gatling.java.bookingBaggage;

import gatling.java.common.RequestBase;

public class GetAllBaggageAvailRq extends RequestBase {
    public String getUrl(String bookingId,String baggageResultId) {
        return "/bookings/" + bookingId + "/flight/baggage/resultSets/"+baggageResultId;
    }
}
