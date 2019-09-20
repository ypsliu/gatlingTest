package gatling.java.ancillaries;

import com.openjaw.api.model.AncillaryAvailRequest;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

public class CreateAncillaryAvailRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/" + bookingId + "/flight/ancillaries/resultSets";
    }

    public String getBody(String mealProductId) {
        AncillaryAvailRequest aar = new AncillaryAvailRequest();
        aar.addFlightProductIdsItem(Integer.parseInt(mealProductId));
        aar.ancillaryType(AncillaryAvailRequest.AncillaryTypeEnum.MEAL);
        aar.setFlightSegmentId(1);
        return Tools.objectTOString(aar);
    }
}
