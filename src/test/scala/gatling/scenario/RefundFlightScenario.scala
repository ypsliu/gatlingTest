package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._

object RefundFlightScenario {
  //取消订单
  val refundFlight = { scenarioName: String =>
    scenario("Refund-Flight")
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking.pause(10, 30),
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingDetailedExec.makeReservation,
        //交钱
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
        //退款
        BookingHistoryExec.retrieveBooking,
        BookingExec.getFlightProductsByBookingId,
        BookingManagementExec.createRefundRemarks,
        BookingManagementExec.createPartialFlightCancellationCost,
        BookingManagementExec.cancelBookingPartialFlight
      )
  }
}
