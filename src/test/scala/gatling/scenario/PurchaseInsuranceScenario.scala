package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._
import scala.concurrent.duration._

object PurchaseInsuranceScenario{

  // 34 已测试

  val oneway_one_customer = { scenarioName: String =>
      scenario("PurchaseInsuranceScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/PurchaseInsuranceScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResultId,
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
        // getBookingSummaries
        BookingHistoryExec.createHistoricBookingSummaries,
        BookingHistoryExec.getHistoricBookingSummaries,

        BookingHistoryExec.retrieveBookingNoBookingId.pause(30 seconds, 90 seconds),
        BookingExec.getBookingByIdCheckBookStatus,
        InsuranceExec.getAllInsuranceAvail,
        BookingProductExec.addInsuranceProduct,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
        // getBookingSummaries
        BookingHistoryExec.createHistoricBookingSummaries,
        BookingHistoryExec.getHistoricBookingSummaries,

        BookingHistoryExec.retrieveBooking,
        BookingExec.getFlightProductsByBookingId,
        //BookingProductExec.removeItem,

    )
  }
}
