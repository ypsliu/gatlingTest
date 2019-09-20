package gatling.java.flight;

import gatling.java.common.RequestBase;
import gatling.java.common.Tools;
import com.openjaw.api.model.*;
import java.util.ArrayList;
import java.util.List;

public class CreateFlightSearchRq extends RequestBase {

    public String getUrl() {
        return "/flight/resultSets";
    }

    public String getBody(String departureDate, String destination, String origin, String cabinClass, String passengerCounts, String flightType, String returnDate){
        FlightSearch flightSearch = new FlightSearch();
        FlightSearchBound flightSearchBound = new FlightSearchBound();
        String codeType = destination.length()>3?"LOCATION_ID":"IATA";

        flightSearchBound.setDepartureDate(Tools.getLocalDate(Integer.parseInt(departureDate)));
        flightSearchBound.setDestination(getCommonLocationCode(destination,codeType));
        flightSearchBound.setOrigin(getCommonLocationCode(origin,codeType));
        flightSearch.addBoundsItem(flightSearchBound);

        if ("Return".equals(flightType)) {
            FlightSearchBound flightSearchBoundReturn = new FlightSearchBound();
            flightSearchBoundReturn.setDepartureDate(Tools.getLocalDate(Integer.parseInt(returnDate)));
            flightSearchBoundReturn.setDestination(getCommonLocationCode(origin,codeType));
            flightSearchBoundReturn.setOrigin(getCommonLocationCode(destination,codeType));
            flightSearch.addBoundsItem(flightSearchBoundReturn);
        }

        flightSearch.setCabinClass(CabinClass.fromValue(cabinClass));
        flightSearch.setCurrencyCode("CNY");
        flightSearch.setPassengerCounts(parsePassengerCounts(passengerCounts));
        flightSearch.setMaxConnections(0);
        flightSearch.setRedemption(false);
        return  Tools.objectTOString(flightSearch);
    }

    public static CommonLocationCode getCommonLocationCode(String add, String context){
        CommonLocationCode commonLocationCode = new CommonLocationCode();
        LocationByCode locationByCode = new LocationByCode();
        locationByCode.setCode(add);
        locationByCode.setContext(LocationByCode.ContextEnum.fromValue(context));
        commonLocationCode.setLocationByCode(locationByCode);
        return commonLocationCode;
    }

    public static List<PassengerTypeCount> parsePassengerCounts(String passengerCounts) {
        String[] a = passengerCounts.split("-");
        List<PassengerTypeCount> r = new ArrayList<>();

        for(int i=0; i<a.length; i++){
            if(a[i] == "0")
                continue;
            PassengerTypeCount p = new PassengerTypeCount();
            p.setCount(Integer.parseInt(a[i]));
            switch (i){
                case 0:
                    p.setPassengerType(PassengerType.ADT);
                    break;
                case 1:
                    p.setPassengerType(PassengerType.CHD);
                    break;
                case 2:
                    p.setPassengerType(PassengerType.INF);
                    break;
                case 3:
                    p.setPassengerType(PassengerType.YTH);
                    break;
            }
            r.add(p);
        }
        return r;
    }
}
