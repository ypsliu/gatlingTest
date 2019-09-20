package gatling.java.bookingCustomer;

import com.openjaw.api.model.Customer;
import com.openjaw.api.model.Customers;
import com.openjaw.api.model.PassengerType;
import com.openjaw.api.model.PersonName;
import com.openjaw.api.model.TravelDocument;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerACsRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/"+bookingId+"/customers";
    }

    public String getBody(){
        Customers customers = new Customers();
        Customer adt = new Customer();

        PersonName pn = new PersonName();
        pn.setFirstName("万方");
        pn.setSurname("安");
        adt.setName(pn);
        adt.setDateOfBirth(Tools.getLastLocalDateByStr("1992-10-04"));
        adt.setNationality("CN");
        adt.setPassengerType(PassengerType.valueOf("ADT"));
        List<Integer> products = new ArrayList<>();
        products.add(1000);
        adt.setProducts(products);
        TravelDocument td = new TravelDocument();
        td.setDocType(TravelDocument.DocTypeEnum.valueOf("PRC_IDENTITY_CARD"));
        td.setDocId("460201199210040099");
        td.setExpiryDate(Tools.getLastLocalDateByStr("2026-10-10"));
        td.setCountry("CN");
        adt.setTravelDocument(td);
        customers.addCustomersItem(adt);
        return Tools.objectTOString(customers);
    }

    private String getLetterFromInt(Integer value){
        String letter = "";
        switch (value){
            case 0:letter = "f";break;
            case 1:letter = "s";break;
            case 2:letter = "t";break;
            case 3:letter = "fo";break;
            case 4:letter = "fi";break;
        }
        return letter;
    }
}
