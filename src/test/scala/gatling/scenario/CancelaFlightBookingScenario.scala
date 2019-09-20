package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._

object CancelaFlightBookingScenario {
  //取消订单
  val cancelFlight = { scenarioName: String =>
    scenario("Cancel-Flight")
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking.pause(10, 30),
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingDetailedExec.makeReservation,
        BookingManagementExec.cancelUnpaidBookingItems
      )
  }
}
