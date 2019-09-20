package gatling.scenario

import gatling.exec.BookingHistoryExec
import io.gatling.core.Predef._
import scala.concurrent.duration._
import gatling.exec._
import io.gatling.core.Predef._

import scala.concurrent.duration._

object BookingHistoryScenario {
  //订单管理中查询近期及历史订单  "scenarioFeeders/HistoricBookingSenario/recent_booking.csv"
  val recent_booking = { scenarioName: String =>
    scenario("BookingHistoryScenario-recent_booking")
      .feed(csv("scenarioFeeders/PayTheOrderScenario/oneway_one_customer.csv").batch.circular)
      .feed(csv("scenarioFeeders/HistoricBookingSenario/recent_booking.csv").batch.circular)
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

        BookingHistoryExec.createHistoricBookingSummaries,
        BookingHistoryExec.getHistoricBookingSummaries
      )
  }

}
