package gatling.java.flight;

import gatling.java.common.RequestBase;

public class GetFlightSearchResultRq extends RequestBase {
    public String getUrl(String resultSetId) {
        return "/flight/resultSets/"+ resultSetId;
    }
}
