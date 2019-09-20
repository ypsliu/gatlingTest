package gatling.java.confirmcostfreeseat;

import gatling.java.common.RequestBase;

public class ConfirmCostFreeSeatRq extends RequestBase {

    public String getUrl(String bookingId){
        return "/bookings/"+bookingId+"/products/costFreeSeats/confirmation";
    }
}
