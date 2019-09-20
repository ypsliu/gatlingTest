package gatling.java.authentication;

import com.openjaw.api.model.ExternalFlightBookingSearch;
import com.openjaw.api.model.PersonName;
import com.openjaw.api.model.SmsAuthentication;
import gatling.java.common.RequestBase;

import static gatling.java.common.Tools.objectTOString;

public class ExternalFlightBookingSearchRq extends RequestBase {

    public String getBody(String documentId,String documentType,String authCode,String mobile,String title,String firstname,String middleName,String surname){
        ExternalFlightBookingSearch bookingSearch = new ExternalFlightBookingSearch();

        bookingSearch.setDocumentId(documentId);
        bookingSearch.documentType(ExternalFlightBookingSearch.DocumentTypeEnum.valueOf(documentType));

        PersonName pname = new PersonName();
        pname.setFirstName(firstname);
        pname.setMiddleName(middleName);
        pname.setSurname(surname);
        pname.setTitle(PersonName.TitleEnum.valueOf(title));
        bookingSearch.setName(pname);

        SmsAuthentication auth = new SmsAuthentication();
        auth.setAuthCode(authCode);
        auth.setMobile(mobile);
        bookingSearch.setSmsAuthentication(auth);

        return objectTOString(bookingSearch);
    }

    public String getUrl(){
        return "/external/bookings/flights/retrieval";
    }
}
