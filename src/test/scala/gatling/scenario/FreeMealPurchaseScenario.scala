package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._


object FreeMealPurchaseScenario{

  // 29

  val oneway_one_customer = { scenarioName: String =>
      scenario("FreeMealPurchaseScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/FreeMealPurchaseScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking.pause(10,30),
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
        BookingHistoryExec.retrieveBooking,
        BookingHistoryExec.retrieveBooking,
        BookingExec.getFlightProductsByBookingId,
        AncillariesExec.createAncillaryAvail,
        AncillariesExec.getAncillaryAvail,
        BookingProductExec.addSpecialRequests,
        BookingExec.makeReservation,
       /* EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
        BookingHistoryExec.retrieveBooking,*/

    )
  }
}
