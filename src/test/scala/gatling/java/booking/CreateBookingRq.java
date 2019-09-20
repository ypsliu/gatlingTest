package gatling.java.booking;

import gatling.java.common.RequestBase;

import java.util.HashMap;
import java.util.Map;

public class CreateBookingRq extends RequestBase {
    public String getUrl(){
        return "/bookings";
    }

}
