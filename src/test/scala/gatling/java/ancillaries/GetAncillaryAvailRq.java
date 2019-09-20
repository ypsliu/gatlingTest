package gatling.java.ancillaries;

import gatling.java.common.RequestBase;

public class GetAncillaryAvailRq extends RequestBase {
    public String getUrl(String bookingId,String mealResultId) {
        return "/bookings/" + bookingId + "/flight/ancillaries/resultSets/" + mealResultId;
    }
}
