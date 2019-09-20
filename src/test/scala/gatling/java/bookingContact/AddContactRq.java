package gatling.java.bookingContact;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;
import com.openjaw.api.model.*;

import java.util.HashMap;
import java.util.Map;

public class AddContactRq extends RequestBase {
    public String getBody(){
        ContactDetails contactDetails = new ContactDetails();
        Address address = new Address();
        address.addAddressLinesItem("test street");
        address.setCity("Beijing");
        Country country = new Country();
        country.setCode("CN");
        country.setName("China");
        address.setCountry(country);
        address.setPostCode("101300");
        address.setState("Peking");
        address.setStreetNmbr("10");
        contactDetails.setAddress(address);

        contactDetails.setEmail("Liqiu.zhang@openjawtech.com");

        PersonName personName = new PersonName();
        personName.setTitle(PersonName.TitleEnum.MRS);
        personName.setFirstName("ContactF");
        personName.setMiddleName("ContactM");
        personName.setSurname("ContactS");
        contactDetails.setName(personName);

        Phone phone = new Phone();
        phone.setAreaCode("0");
        phone.setCountryCode("23");
        phone.setNumber("13883051149");
        phone.setPhoneType(Phone.PhoneTypeEnum.MOBILE);
        contactDetails.addPhonesItem(phone);

        return Tools.objectTOString(contactDetails);
    }

    public String getUrl(String bookingId){
        return "/bookings/"+ bookingId + "/contact";
    }
}
