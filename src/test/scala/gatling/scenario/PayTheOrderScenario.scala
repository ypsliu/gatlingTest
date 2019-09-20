package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._

object PayTheOrderScenario{

  // 13 已测试

  val oneway_one_customer = { scenarioName: String =>
      scenario("PayTheOrderScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/PayTheOrderScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation,
        BookingHistoryExec.retrieveBooking,
        BookingExec.getFlightProductsByBookingId,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect
    )
  }
}
