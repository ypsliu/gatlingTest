package gatling.java.bookingSeat;

import gatling.java.common.RequestBase;

public class BookingSeatMapRq extends RequestBase {

    public String getUrl(String bookingId,String productId,String flightSegmentId) {
        return "/bookings/"+bookingId+"/flight/seatMaps?productId="+productId+"&flightSegmentId="+flightSegmentId;
    }
}
