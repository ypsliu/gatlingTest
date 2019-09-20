package gatling.scenario

import gatling.exec._
import gatling.common.SeatFreeChargeTypeEnum._
import io.gatling.core.Predef._

import scala.concurrent.duration._

object BookingPaymentSeatScenario {
    //订单管理中附加服务付费座位购买
    val payment_seat = { scenarioName: String =>
        scenario("BookingPaymentSeatScenario-payment_seats")
          .feed(seat_charge_type)
          .feed(csv("scenarioFeeders/FlightBookingScenario/oneway_one_customer.csv").batch.circular)
          .exec(
              AuthenticationExec.login,
              FlightExec.createFlightSearch,
              FlightExec.getFlightSearchResult.pause(2 seconds, 3 seconds),
              BookingExec.createBooking,
              BookingProductExec.addFlightProduct.pause(2 seconds, 5 seconds),
              //addCustomers
              BookingCustomerExec.addCustomers.pause(1 seconds, 3 seconds),
              BookingContactExec.addContact.pause(1 seconds, 3 seconds),
              BookingDetailedExec.makeReservation.pause(1 seconds, 3 seconds),
              EzpayExec.authoriseRedirect.pause(1 seconds, 3 seconds),
              EzpayExec.redirectUrl.pause(1 seconds, 3 seconds),
              EzpayExec.get_detail.pause(1 seconds, 3 seconds),
              EzpayExec.confirmRedirect.pause(1 seconds, 3 seconds),
              BookingHistoryExec.retrieveBooking.pause(1 seconds, 3 seconds),
              BookingDetailedExec.getBookingById,
              PaymentSeatExec.getSeatMap,
              PaymentSeatExec.assignSeats,
              BookingDetailedExec.makeReservation.pause(1 seconds, 3 seconds),
              EzpayExec.authoriseRedirect.pause(1 seconds, 3 seconds),
              EzpayExec.redirectUrl.pause(1 seconds, 3 seconds),
              EzpayExec.get_detail.pause(1 seconds, 3 seconds),
              EzpayExec.confirmRedirect.pause(1 seconds, 3 seconds),
              BookingHistoryExec.retrieveBooking,
              BookingDetailedExec.getBookingById
          )
    }
}
