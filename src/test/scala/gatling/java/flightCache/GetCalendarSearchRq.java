package gatling.java.flightCache;

import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class GetCalendarSearchRq extends RequestBase {
    public String getUrl(String iataCode, String departureDate, String returnDate) {
        departureDate = Tools.emptyStrTranZero(departureDate);
        returnDate    = Tools.emptyStrTranZero(returnDate);
        return "/flightCache/calendarSearch?from=" + iataCode + "&departureDate=" + Tools.getDate(Integer.parseInt(departureDate)) + "&returnDate=" + Tools.getDate(Integer.parseInt(returnDate));
    }
}
