package gatling.java.bookingBaggage;

import gatling.java.common.RequestBase;
import gatling.java.common.Tools;
import com.openjaw.api.model.*;

import java.util.List;
import java.util.ArrayList;

public class BaggageAvailRequestRq  extends RequestBase {
    public String getBody(String baggageProductId){

        BaggageAvailRequest baggage = new BaggageAvailRequest();
        List<Integer> lists = new ArrayList<>();
        lists.add(Integer.parseInt(baggageProductId));
        baggage.setFlightProductIds(lists);
        return Tools.objectTOString(baggage);
    }

    public String getUrl(String bookingId){
        return "/bookings/"+ bookingId + "/flight/baggage/resultSets";
    }
}
