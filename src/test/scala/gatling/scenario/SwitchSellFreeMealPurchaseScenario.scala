package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._


object SwitchSellFreeMealPurchaseScenario {

  // 40 已测试

  val oneway_one_customer = { scenarioName: String =>
      scenario("SwitchSellFreeMealPurchaseScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/SwitchSellFreeMealPurchaseScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking.pause(10,30),
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation,
        BookingExec.getFlightProductsByBookingId,
        AncillariesExec.createAncillaryAvail,
        AncillariesExec.getAncillaryAvail,
        BookingProductExec.addSpecialRequests,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
    )
  }
}
