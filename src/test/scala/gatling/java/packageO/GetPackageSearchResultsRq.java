package gatling.java.packageO;

import gatling.java.common.RequestBase;

public class GetPackageSearchResultsRq extends RequestBase {
    public String getUrl(String package_resultSetId) {
        return "/package/resultSets/" + package_resultSetId;
    }

}
