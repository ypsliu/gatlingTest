package gatling.scenario

import gatling.exec.{EzpayExec, _}
import io.gatling.core.Predef.{csv, scenario, _}

import scala.concurrent.duration._

object BookingPaymentBaggageScenario {

  val payment_baggage = { scenarioName: String =>
    scenario("BookingPaymentBaggageScenario-pay_paymentbaggage")
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
        EzpayExec.authoriseRedirect.pause(1 seconds, 3 seconds),
        EzpayExec.redirectUrl.pause(1 seconds, 3 seconds),
        EzpayExec.get_detail.pause(1 seconds, 3 seconds),
        EzpayExec.confirmRedirect.pause(1 seconds, 3 seconds),
        //createHistoricBookingSummaries  有改动
        BookingHistoryExec.createSpecialHistoricBookingSummaries.pause(1 seconds, 3 seconds),
        //getHistoricBookingSummaries  有改动
        BookingHistoryExec.getHistoricBookingSpecialSummaries.pause(1 seconds, 3 seconds),
        BookingHistoryExec.retrieveBooking.pause(1 seconds, 3 seconds),
        // getBookingById  有改动
        BookingDetailedExec.getBaggageProductIdByBookingById.pause(1 seconds, 3 seconds),
        FlightBaggageExec.createBaggageAvail.pause(1 seconds, 3 seconds),
        FlightBaggageExec.getAllBaggageAvail.pause(1 seconds, 3 seconds),
        FlightBaggageExec.addBaggageProduct.pause(1 seconds, 3 seconds),
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect.pause(1 seconds, 3 seconds),
        EzpayExec.redirectUrl.pause(1 seconds, 3 seconds),
        EzpayExec.get_detail.pause(1 seconds, 3 seconds),
        EzpayExec.confirmRedirect.pause(1 seconds, 3 seconds)
      )
  }
}