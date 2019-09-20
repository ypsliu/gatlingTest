package gatling.java.insurance;

import gatling.java.common.RequestBase;

public class GetAllInsuranceAvailRq extends RequestBase {

    public String getUrl(String bookingId, String productId) {
        return "/bookings/"+bookingId+"/products/"+productId+"/crossSell/insurance";
    }

}
