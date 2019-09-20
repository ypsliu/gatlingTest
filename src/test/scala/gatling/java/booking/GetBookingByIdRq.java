package gatling.java.booking;

import gatling.java.common.RequestBase;
import sun.misc.Request;

public class GetBookingByIdRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/" + bookingId;
    }
}

