package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._


object OMNIPurchaseInsuranceScenario{
  // 47 未测试
  val oneway_one_customer = { scenarioName: String =>
      scenario("OMNIPurchaseInsuranceScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/OMNIPurchaseInsuranceScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.sendMobileAuthentication,
        AuthenticationExec.loginB,
        AuthenticationExec.retrieveExternalFlightBookings,
        BookingExec.createBookingWithExternalFlightBooking,
        BookingContactExec.updateContact,
        BookingExec.getFlightProductsByBookingId,
        InsuranceExec.getAllInsuranceAvail,
        BookingProductExec.addInsuranceProduct,
        BookingExec.makeReservation,
        BookingHistoryExec.retrieveBooking,
        BookingExec.getFlightProductsByBookingId,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
        BookingHistoryExec.retrieveBooking,
        BookingExec.getFlightProductsByBookingId,

    )
  }
}
