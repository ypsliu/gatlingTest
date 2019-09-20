package gatling.java.bookingHistory;

import com.openjaw.api.model.ArchivedBookingSearch;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class CreateArchivedHistoricBookingSummariesRq extends RequestBase {
    public String getUrl(){
        return "/bookingHistory/archive/resultSets";
    }
    public String getBody(String startDate,String endDate){
        ArchivedBookingSearch booking = new ArchivedBookingSearch();
        booking.setRedemptionPayment(false);
        booking.setStartDate(Tools.getLastLocalDate(Integer.parseInt(startDate)));
        booking.setEndDate(Tools.getLastLocalDate(Integer.parseInt(endDate)));
        return Tools.objectTOString(booking);
    }
}
