package gatling.java.bookingPackage;

import gatling.java.common.RequestBase;
import gatling.java.common.Tools;
import com.openjaw.api.model.PackageProduct;

import java.util.ArrayList;
import java.util.Map;

public class AddPackageRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/"+ bookingId +"/package";
    }

    public String getBody(String package_OptionsId,String package_resultSetId) {
        PackageProduct packageProduct = new PackageProduct();
        packageProduct.carExtrasIds(new ArrayList<>());
        packageProduct.hotelExtrasIds(new ArrayList<>());
        packageProduct.setPackageId(package_OptionsId);
        packageProduct.setResultSetId(package_resultSetId);
        return Tools.objectTOString(packageProduct);
    }
}
