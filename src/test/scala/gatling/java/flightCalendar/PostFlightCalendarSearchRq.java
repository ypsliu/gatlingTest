package gatling.java.flightCalendar;

import com.openjaw.api.model.CabinClass;
import com.openjaw.api.model.FlightSearch;
import gatling.java.flight.CreateFlightSearchRq;
import com.openjaw.api.model.FlightSearchBound;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class PostFlightCalendarSearchRq extends RequestBase {

    public String getUrl() {
        return "/flight/calendar";
    }

    public String getBody(String currentCode, String departureDate,
                          String destination, String origin, String cabinClass,
                          String passengerCounts, String flightType, String returnDate) {
        FlightSearch flightSearch = new FlightSearch();
        FlightSearchBound bond = new FlightSearchBound();
        bond.setDepartureDate(Tools.getLocalDate(Integer.parseInt(departureDate)));
        bond.setDestination(CreateFlightSearchRq.getCommonLocationCode(destination, "IATA"));
        bond.setOrigin(CreateFlightSearchRq.getCommonLocationCode(origin, "IATA"));
        bond.setDaysBefore(2);
        bond.setDaysAfter(2);
        flightSearch.addBoundsItem(bond);
        if ("Return".equals(flightType)) {
            bond = new FlightSearchBound();
            bond.setDepartureDate(Tools.getLocalDate(Integer.parseInt(returnDate)));
            bond.setDestination(CreateFlightSearchRq.getCommonLocationCode(origin, "IATA"));
            bond.setOrigin(CreateFlightSearchRq.getCommonLocationCode(destination, "IATA"));
            bond.setDaysBefore(2);
            bond.setDaysAfter(2);
            flightSearch.addBoundsItem(bond);
        }
        flightSearch.setCabinClass(CabinClass.fromValue(cabinClass));
        flightSearch.setCurrencyCode(currentCode);
        flightSearch.setPassengerCounts(CreateFlightSearchRq.parsePassengerCounts(passengerCounts));
        flightSearch.setMaxConnections(0);
        return Tools.objectTOString(flightSearch);
    }
}
