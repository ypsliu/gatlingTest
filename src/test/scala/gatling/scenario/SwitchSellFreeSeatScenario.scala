package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._
import scala.concurrent.duration._
import gatling.common.SeatFreeChargeTypeEnum._

object SwitchSellFreeSeatScenario {

  val free_seat_switchsell = { scenarioName: String =>
    scenario("SwitchSellFreeSeatScenario-free_seat")
      .feed(seat_free_type)
      .feed(csv("scenarioFeeders/FlightBookingScenario/oneway_one_customer.csv").batch.circular)
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
