package gatling.java.bookingHistory;

import com.openjaw.api.model.BookingSearch;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

import java.util.ArrayList;
import java.util.List;
import  java.time.LocalDate;

public class CreateHistoricBookingSummariesRq extends RequestBase {
    public String getUrl(){
        return "/bookingHistory/resultSets";
    }
    public String getBody(String startDate , String endDate){
        BookingSearch booking = new BookingSearch();
        booking.setContainsAncillary(false);
        List<BookingSearch.ContainsProductTypeEnum> containsProductType = new ArrayList<>();
        containsProductType.add(BookingSearch.ContainsProductTypeEnum.AIR);
        booking.setContainsProductType(containsProductType);
        booking.setRedemptionPayment(false);
        booking.setStartDate(Tools.getLastLocalDate(Integer.parseInt(startDate)));//Tools.getLastLocalDate(Integer.parseInt(startDate))
        booking.setEndDate(Tools.getLastLocalDate(Integer.parseInt(endDate)));//Tools.getLastLocalDate(Integer.parseInt(endDate))
        return Tools.objectTOString(booking);
    }

    public String getSpecialBody(){
        BookingSearch booking = new BookingSearch();
        booking.setStartDate(Tools.getLocalDate(-2));//Tools.getLastLocalDate(Integer.parseInt(startDate))
        booking.setEndDate(Tools.getLocalDate(0));//Tools.getLastLocalDate(Integer.parseInt(endDate))
        return Tools.objectTOString(booking);
    }
}
