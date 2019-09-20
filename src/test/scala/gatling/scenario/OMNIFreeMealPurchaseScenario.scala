package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._


object OMNIFreeMealPurchaseScenario {
  // 46 未测试
  val oneway_one_customer = { scenarioName: String =>
    scenario("OMNIFreeMealPurchaseScenario-oneway_one_customer")
      .feed(csv("scenarioFeeders/OMNIFreeMealPurchaseScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.sendMobileAuthentication,
        AuthenticationExec.loginB,
        AuthenticationExec.retrieveExternalFlightBookings,
        BookingExec.createBookingWithExternalFlightBooking,
        BookingContactExec.updateContact,
        BookingExec.getFlightProductsByBookingId,
        AncillariesExec.createAncillaryAvail,
        AncillariesExec.getAncillaryAvail,
        BookingProductExec.addSpecialRequests,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        BookingHistoryExec.retrieveBooking,
        BookingExec.getFlightProductsByBookingId,
      )
  }

}
