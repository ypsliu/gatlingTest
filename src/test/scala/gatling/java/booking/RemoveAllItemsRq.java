package gatling.java.booking;

public class RemoveAllItemsRq {

    public String getUrl(String bookingId) {
        return "/bookings/"+bookingId;
    }
}
