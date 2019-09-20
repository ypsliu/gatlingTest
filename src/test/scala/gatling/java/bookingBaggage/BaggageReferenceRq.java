package gatling.java.bookingBaggage;

import com.openjaw.api.model.BaggageReference;
import com.openjaw.api.model.CustomerResultWithPrice;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

import java.util.ArrayList;
import java.util.List;

public class BaggageReferenceRq extends RequestBase {

    public String getUrl(String bookingId) {
        return "/bookings/" + bookingId + "/products/baggage";
    }

    public String getBody(String resultSetId){
        BaggageReference br = new BaggageReference();
        br.setResultSetId(resultSetId);
        CustomerResultWithPrice c = new CustomerResultWithPrice();
        c.setCustomerId(1);
        c.setPriceId(0);
        c.setResultId("2");
        List<CustomerResultWithPrice> lists = new ArrayList<>();
        lists.add(c);
        br.setResults(lists);
        return Tools.objectTOString(br);
    }
}
