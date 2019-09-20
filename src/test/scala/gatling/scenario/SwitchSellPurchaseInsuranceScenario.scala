package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._


object SwitchSellPurchaseInsuranceScenario {
  // 41 已测试
  val oneway_one_customer = { scenarioName: String =>
      scenario("SwitchSellPurchaseInsuranceScenario-oneway_one_customer")
        .feed(csv("scenarioFeeders/SwitchSellPurchaseInsuranceScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation,
        BookingExec.getFlightProductsByBookingId,
        InsuranceExec.getAllInsuranceAvail,
        BookingProductExec.addInsuranceProduct,
        BookingExec.makeReservation,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect,
        BookingHistoryExec.retrieveBooking,
        BookingExec.getBookingByIdCheckBookStatus

    )
  }
}
