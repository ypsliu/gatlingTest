package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef.{csv, scenario, _}
import scala.concurrent.duration._
import gatling.common.SeatFreeChargeTypeEnum._

object SwitchSellPaymentSeatScenario {

  val payment_seat_switchsell = { scenarioName: String =>
    scenario("SwitchSellPaymentSeatScenario-pay_paymentseat")
      .feed(seat_charge_type)
      .feed(csv("scenarioFeeders/FixedDateFlightSearchScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch.pause(1 seconds, 3 seconds),
        FlightExec.getFlightSearchResult.pause(1 seconds, 3 seconds),
        BookingExec.createBooking.pause(1 seconds, 3 seconds),
        BookingProductExec.addFlightProduct.pause(1 seconds, 3 seconds),
        BookingCustomerExec.addCustomers.pause(1 seconds, 3 seconds),
        BookingContactExec.addContact.pause(1 seconds, 3 seconds),
        BookingExec.makeReservation,
        // getBookingById  有改动
        BookingDetailedExec.getSeatProductIdByBookingById.pause(3 seconds, 5 seconds),
        //有问题  需要接入环境跑下，确认数据
        PaymentSeatExec.getSeatMap,
        PaymentSeatExec.assignSeats,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect.pause(1 seconds, 3 seconds),
        EzpayExec.redirectUrl.pause(1 seconds, 3 seconds),
        EzpayExec.get_detail.pause(1 seconds, 3 seconds),
        EzpayExec.confirmRedirect.pause(1 seconds, 3 seconds)
      )
  }
}
