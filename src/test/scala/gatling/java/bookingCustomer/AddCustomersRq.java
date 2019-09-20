package gatling.java.bookingCustomer;
import gatling.java.common.CommonFunc;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;
import com.openjaw.api.model.*;

import java.util.*;

public class AddCustomersRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/"+bookingId+"/customers";
    }

    public String getBody(String passengerCounts, String productId, String flightType){
        Customers customers = new Customers();
        List<PassengerTypeCount> ps = CommonFunc.parsePassengerCounts(passengerCounts);
        for (int i = 0;i<ps.size();i++){
            PassengerType t = ps.get(i).getPassengerType();
            if (ps.get(i).getCount() == 0)
                continue;
            for (int c=0;c<ps.get(i).getCount();c++){
                Customer customer = new Customer();
                customer.setPassengerType(t);
                customer.addProductsItem(Integer.parseInt(productId));
                PersonName personName = new PersonName();
                String l = getLetterFromInt(c);
                int age = 0;
                switch (t){
                    case ADT:
                        //customer.setAge(18);
                        age = 18;
                        if (flightType == "I") {
                            if (c % 2 == 0) {
                                personName.setTitle(PersonName.TitleEnum.MRS);
                            } else {
                                personName.setTitle(PersonName.TitleEnum.MR);
                            }
                        }
                        personName.setFirstName("Adtf"+l);
                        personName.setMiddleName("Adtm"+l);
                        personName.setSurname("Adts"+l);
                        break;
                    case CHD:
                        //customer.setAge(5);
                        age = 5;
                        personName.setFirstName("Chdf"+l);
                        personName.setMiddleName("Chdm"+l);
                        personName.setSurname("Chds"+l);
                        break;
                    case INF:
                        //customer.setAge(1);
                        age = 1;
                        personName.setFirstName("Inff"+l);
                        personName.setMiddleName("Infm"+l);
                        personName.setSurname("Infs"+l);
                        break;
                    case YTH:
                        //customer.setAge(13);
                        age = 13;
                        personName.setFirstName("Ythf"+l);
                        personName.setMiddleName("Ythm"+l);
                        personName.setSurname("Yths"+l);
                        break;
                }
                customer.setDateOfBirth(Tools.getLocalDate(0-age*365));
                customer.setName(personName);

                TravelDocument travelDocument = new TravelDocument();
                if (flightType == "I") {
                    if (c % 2 == 0) {
                        customer.setGender(Customer.GenderEnum.FEMALE);
                        //travelDocument.setGender(Gender.FEMALE);
                    } else {
                        customer.setGender(Customer.GenderEnum.MALE);
                        //travelDocument.setGender(Gender.MALE);
                    }
                }
                travelDocument.setDocId("GD"+(int)(111111+(Math.random()*88888)));
                travelDocument.setDocType(TravelDocument.DocTypeEnum.PASSPORT);
                travelDocument.setCountry("CN");
                travelDocument.setExpiryDate(Tools.getLocalDate(10*365));
                travelDocument.setNationality("CN");
                customer.setTravelDocument(travelDocument);
                customer.setNationality("CN");
                customers.addCustomersItem(customer);
            }
        }
        return Tools.objectTOString(customers);
    }

    public String getBody(String passengerCounts, String flightType, Integer... packages){
        Customers customers = new Customers();
        List<PassengerTypeCount> ps = CommonFunc.parsePassengerCounts(passengerCounts);
        for (int i = 0;i<ps.size();i++){
            PassengerType t = ps.get(i).getPassengerType();
            if (ps.get(i).getCount() == 0)
                continue;
            for (int c=0;c<ps.get(i).getCount();c++){
                Customer customer = new Customer();
                customer.setPassengerType(t);
                PersonName personName = new PersonName();
                String l = getLetterFromInt(c);
                int age = 0;
                switch (t){
                    case ADT:
                        age = 25;
                        customer.setAge(age);
                        if (flightType == "I") {
                            if (c % 2 == 0) {
                                personName.setTitle(PersonName.TitleEnum.MRS);
                            } else {
                                personName.setTitle(PersonName.TitleEnum.MR);
                            }
                        }
                        personName.setFirstName("Adultf"+l);
                        personName.setMiddleName("Adultm"+l);
                        personName.setSurname("Adults"+l);
                        break;
                    case CHD:
                        //customer.setAge(5);
                        age = 5;
                        personName.setFirstName("Childf"+l);
                        personName.setMiddleName("Childm"+l);
                        personName.setSurname("Childs"+l);
                        break;
                    case INF:
                        //customer.setAge(1);
                        age = 1;
                        personName.setFirstName("I_nfantf"+l);
                        personName.setMiddleName("I_nfantm"+l);
                        personName.setSurname("I_nfants"+l);
                        break;
                    case YTH:
                        //customer.setAge(13);
                        age = 13;
                        personName.setFirstName("Youthf"+l);
                        personName.setMiddleName("Youthm"+l);
                        personName.setSurname("Youths"+l);
                        break;
                }
                customer.setDateOfBirth(Tools.getLocalDate(0-age*365));
                customer.setName(personName);

                TravelDocument travelDocument = new TravelDocument();
                if (flightType == "I") {
                    if (c % 2 == 0) {
                        customer.setGender(Customer.GenderEnum.FEMALE);
                        //travelDocument.setGender(Gender.FEMALE);
                    } else {
                        customer.setGender(Customer.GenderEnum.MALE);
                        //travelDocument.setGender(Gender.MALE);
                    }
                }
                travelDocument.setDocId("GD"+(int)(111111+(Math.random()*88888)));
                travelDocument.setDocType(TravelDocument.DocTypeEnum.PASSPORT);
                travelDocument.setCountry("CN");
                travelDocument.setExpiryDate(Tools.getLocalDate(10*365));
                travelDocument.setNationality("CN");
                customer.setTravelDocument(travelDocument);
                customer.setNationality("CN");

                if(packages.length >= 2){
                    CustomerPackage cp = new CustomerPackage();
                    cp.setId(packages[0]);// packageId
                    cp.addProductsItem(packages[1]);// flightProductId
                    cp.addProductsItem(packages[2]);// hotelProductId
                    customer.addPackagesItem(cp);
                }

                customers.addCustomersItem(customer);
            }
        }
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
