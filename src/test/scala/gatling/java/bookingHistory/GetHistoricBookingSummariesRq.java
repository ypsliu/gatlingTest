package gatling.java.bookingHistory;

import gatling.java.common.RequestBase;

public class GetHistoricBookingSummariesRq extends RequestBase {
    public String getUrl(String resultSetId) {
        return "/bookingHistory/resultSets/"+resultSetId;
    }

}
