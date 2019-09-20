package gatling.java.bookingProduct;

import gatling.java.common.RequestBase;

public class RemoveItemRq extends RequestBase {

    public String getUrl(String bookingId,String productId) {
        return "/bookings/" + bookingId + "/products/"+productId;
    }
}