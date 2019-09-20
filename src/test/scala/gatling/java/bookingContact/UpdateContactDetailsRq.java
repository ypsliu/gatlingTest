package gatling.java.bookingContact;

import com.openjaw.api.model.Address;
import com.openjaw.api.model.Country;
import com.openjaw.api.model.PersonName;
import com.openjaw.api.model.Phone;
import gatling.java.common.RequestBase;
import com.openjaw.api.model.ContactDetails;
import gatling.java.common.Tools;

public class UpdateContactDetailsRq extends RequestBase {

    public String getUrl(String bookingId) {
        return "/bookings/" + bookingId + "/contact";
    }
    /*
    String addressline, String addresscity, String addresscountrycode, String addresscountryname, String addresspostcode,
                          String addressstate, String addressstreetnmbr, String contactemail, String contactfirstname, String contactmiddlename,
                          String contactsurname, String contacttitle, String contactphoneareacode, String contactphonecountrycode, String contactphonenumber,
                          String contactphonetype*/
    public String getBody() {
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
}
