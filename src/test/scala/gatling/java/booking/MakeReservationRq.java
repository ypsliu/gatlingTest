package gatling.java.booking;

import gatling.java.common.RequestBase;

import java.util.HashMap;
import java.util.Map;

public class MakeReservationRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/"+bookingId+"/reservation";
    }

}
