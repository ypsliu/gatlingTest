package gatling.java.bookingHistory;

import gatling.java.common.RequestBase;

public class GetArchivedHistoricBookingSummariesRq extends RequestBase {
    public String getUrl(String resultSetId) {
        return "/bookingHistory/archive/resultSets/"+resultSetId;
    }
}
