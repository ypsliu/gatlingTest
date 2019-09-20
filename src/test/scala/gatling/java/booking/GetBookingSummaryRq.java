package gatling.java.booking;

public class GetBookingSummaryRq {
    public String getUrl(String bookingId) {
        return "/bookings/"+bookingId+"/summary";
    }
}
