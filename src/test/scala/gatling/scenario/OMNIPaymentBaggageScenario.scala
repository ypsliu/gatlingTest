package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef.{csv, scenario}
import scala.concurrent.duration._

object OMNIPaymentBaggageScenario {

  //全渠道附加服务付费行李购买
  val payment_baggage_omni = { scenarioName: String =>
    scenario("OMNIPaymentBaggageScenario-payment_baggage")
      .exec(
        //AuthenticationExec.sendMobileAuthentication,
        AuthenticationExec.loginB,
        //注意：此方法包含造其他订单出票信息
        AuthenticationExec.retrieveExternalFlightBookings,
        BookingExec.createBookingWithExternalFlightBooking,
        BookingDetailedExec.getBaggageProductIdByBookingById.pause(5 seconds, 9 seconds),
        BookingContactExec.updateContact,
        BookingDetailedExec.getBaggageProductIdByBookingById.pause(5 seconds, 9 seconds),
        BookingDetailedExec.getCrossSellAvailabilities,
        FlightBaggageExec.createBaggageAvail,
        FlightBaggageExec.addBaggageProduct,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect
      )
  }
}
