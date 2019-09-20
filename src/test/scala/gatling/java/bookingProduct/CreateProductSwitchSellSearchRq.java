package gatling.java.bookingProduct;

import gatling.java.common.RequestBase;

public class CreateProductSwitchSellSearchRq extends RequestBase {
    public String getUrl(String bookingId,String flight_productId) {
        return "/bookings/"+bookingId+"/products/"+flight_productId+"/switchSell/package";
    }

}
