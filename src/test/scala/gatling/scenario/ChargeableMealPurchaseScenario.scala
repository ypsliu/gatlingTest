package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._


object ChargeableMealPurchaseScenario{

  // 28

  val oneway_one_customer = { scenarioName: String =>
      scenario("ChargeableMealPurchaseScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/ChargeableMealPurchaseScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
        BookingHistoryExec.createHistoricBookingSummaries,
        BookingHistoryExec.getHistoricBookingSummaries,
        BookingHistoryExec.retrieveBooking,
        BookingExec.getFlightProductsByBookingId,
        AncillariesExec.createAncillaryAvail,
        AncillariesExec.getAncillaryAvail,
        BookingProductExec.addAncillaryProduct,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect

    )
  }
}
