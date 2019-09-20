package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._

import scala.concurrent.duration._


object SwitchSellHotelScenario{

  // 35 已测试

  val oneway_one_customer = { scenarioName: String =>
      scenario("SwitchSellHotelScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/SwitchSellHotelScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login.pause(3 seconds, 10 seconds),
        FlightExec.createFlightSearch.pause(3 seconds, 10 seconds),
        FlightExec.getFlightSearchResultId.pause(3 seconds, 8 seconds),
        BookingExec.createBooking.pause(3 seconds, 10 seconds),
        BookingProductExec.addFlightProduct.pause(3 seconds, 10 seconds),
        BookingProductExec.createProductSwitchSellSearch.pause(3 seconds, 10 seconds),
        PackageExec.getPackageSearchResults.pause(3 seconds, 10 seconds),
        BookingPackageExec.addPackage.pause(3 seconds, 10 seconds),
        BookingExec.getPackageProductsByBookingId.pause(3 seconds, 5 seconds),
        BookingCustomerExec.addPackagesCustomers.pause(10 seconds, 20 seconds),
        BookingContactExec.addContact.pause(1 seconds, 5 seconds),
        BookingExec.makeReservation.pause(3 seconds, 10 seconds),
        EzpayExec.authoriseRedirect.pause(1 seconds, 10 seconds),
        EzpayExec.redirectUrl.pause(3 seconds, 10 seconds),
        EzpayExec.get_detail.pause(3 seconds, 10 seconds),
        EzpayExec.confirmRedirect
    )
  }
}
