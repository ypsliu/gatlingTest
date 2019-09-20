package gatling.java.packageO;

import com.openjaw.api.model.*;
import com.openjaw.api.model.PackageSearch.PackageSearchTypeEnum;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class CreatePackageSearchRq extends RequestBase {
    public String getUrl() {
        return "/package/resultSets";
    }

    public String getBody(String originCode,String destinationCode,String departureDate, String returnDate,
                          String packageSearchType) {
        // 创建 origin
        CommonLocationCode origin = new CommonLocationCode();
        LocationByCode originLocationByCode = new LocationByCode()
                .code(originCode).context(LocationByCode.ContextEnum.LOCATION_ID);
        origin.setLocationByCode(originLocationByCode);

        // 创建 destination
        CommonLocationCodeOrCoordinates destination = new CommonLocationCodeOrCoordinates();
        LocationByCode destinationLocationByCode = new LocationByCode()
                .code(destinationCode).context(LocationByCode.ContextEnum.LOCATION_ID);
        destination.setLocationByCode(destinationLocationByCode);

        // 创建 flight
        PackageSearchFlight flight = new PackageSearchFlight();
        flight.cabinClass(CabinClass.ECONOMY).departureDate(Tools.getLocalDate(Integer.parseInt(departureDate)))
                .maxConnections(0).returnDate(Tools.getLocalDate(Integer.parseInt(returnDate)));

        // 创建 hotel
        PackageSearchHotel hotel = new PackageSearchHotel();
        HotelSearchRoom hsr = new HotelSearchRoom();
        hsr.adults(1).addChildrenItem(new SearchChild().age(10));
        hotel.checkinDate(Tools.getLocalDate(Integer.parseInt(departureDate)))
                .checkoutDate(Tools.getLocalDate(Integer.parseInt(returnDate))).addRoomsItem(hsr);

        // 创建 passengerCounts
        PassengerTypeCount ptcADT = new PassengerTypeCount();
        ptcADT.count(1).passengerType(PassengerType.ADT);
        PassengerTypeCount ptcCHD = new PassengerTypeCount();
        ptcCHD.count(1).passengerType(PassengerType.CHD);

        PackageSearch ps = new PackageSearch();
        ps.currencyCode("CNY").origin(origin).destination(destination).flight(flight).hotel(hotel);
        ps.addPassengerCountsItem(ptcADT).addPassengerCountsItem(ptcCHD);
        ps.setPackageSearchType(PackageSearchTypeEnum.fromValue(packageSearchType.toUpperCase()));
        ps.switchSell(false);
        //ps.setHotelCityType(PackageSearch.HotelCityTypeEnum.DEPARTURE_CITY);
        return Tools.objectTOString(ps);
    }
}
