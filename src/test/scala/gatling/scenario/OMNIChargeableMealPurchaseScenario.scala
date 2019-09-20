package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._


object
OMNIChargeableMealPurchaseScenario{
  // 45 未测试
  val oneway_one_customer = { scenarioName: String =>
      scenario("OMNIChargeableMealPurchaseScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/OMNIChargeableMealPurchaseScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.sendMobileAuthentication,
        AuthenticationExec.loginB,
        AuthenticationExec.retrieveExternalFlightBookings,
        BookingExec.createBookingWithExternalFlightBooking,
        BookingExec.getFlightProductsByBookingId,
        BookingContactExec.updateContact,
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
