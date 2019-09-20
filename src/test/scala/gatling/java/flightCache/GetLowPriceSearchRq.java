package gatling.java.flightCache;

import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class GetLowPriceSearchRq extends RequestBase {

    public String getUrl(String from, String departureDate, String returnDate) {

        return "/flightCache/lowPriceSearch?from=" + from +
                "&departureDate=" + Tools.getLocalDate(Integer.parseInt(departureDate)).toString() +
                "&returnDate=" + Tools.getLocalDate(Integer.parseInt(returnDate));
    }

}
