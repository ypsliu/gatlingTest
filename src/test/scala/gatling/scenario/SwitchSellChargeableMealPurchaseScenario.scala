package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._


object SwitchSellChargeableMealPurchaseScenario {

  // 39 已测试

  val oneway_one_customer = { scenarioName: String =>
      scenario("SwitchSellChargeableMealPurchaseScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/SwitchSellChargeableMealPurchaseScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation,
        BookingExec.getFlightProductsByBookingId,
        AncillariesExec.createAncillaryAvail,
        AncillariesExec.getAncillaryAvail,
        BookingProductExec.addAncillaryProduct,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
    )
  }
}
