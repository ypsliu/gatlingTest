package gatling.java.bookingHistory;

import com.openjaw.api.model.Booking;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class RetrieveBookingRq extends RequestBase {
    public String getUrl() {
        return "/bookingHistory/retrieval";
    }

    public String getBody(String bookingReference){
        Booking booking = new Booking();
        booking.setBookingReference(bookingReference);
        return Tools.objectTOString(booking);
    }
}
