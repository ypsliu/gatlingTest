package gatling.java.bookingProduct;

import com.openjaw.api.model.CustomerResultWithQuantity;
import com.openjaw.api.model.InsuranceReference;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class AddInsuranceProductRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/" + bookingId + "/products/insurance";
    }

    public String getBody(String passengerCounts, String resultSetId) {
        String[] nums = passengerCounts.split("-");
        int total = 0;
        for (String str: nums) {
            total = total + Integer.parseInt(str);
        }

        InsuranceReference ir = new InsuranceReference();
        ir.setResultSetId(resultSetId);
        CustomerResultWithQuantity crwq = null;
        for (int i = 1; i <= total; i++) {
            crwq = new CustomerResultWithQuantity();
            crwq.setCustomerId(i);
            crwq.setQuantity(1);
            crwq.setResultId("" + i);
            ir.addResultsItem(crwq);
        }
        return Tools.objectTOString(ir);
    }
}