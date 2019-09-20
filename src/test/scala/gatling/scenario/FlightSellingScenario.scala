package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._

import scala.concurrent.duration._

object FlightSellingScenario {

  val oneway_one_customer = { scenarioName: String =>
    scenario("FlightSellingScenario-oneway_one_customer")
      .feed(csv("scenarioFeeders/FlightSellingScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login.pause(3 second,10 seconds),
        FlightExec.createFlightSearch.pause(10 second, 20 seconds),
        FlightExec.getFlightSearchResult.pause(5 seconds, 8 seconds),
        BookingExec.createBooking.pause(5 seconds, 8 seconds),
        BookingProductExec.addFlightProduct.pause(5 seconds, 10 seconds),
        BookingCustomerExec.addCustomers.pause(10 seconds, 20 seconds),
        BookingContactExec.addContact.pause(10 seconds, 20 seconds),
        BookingExec.makeReservation.pause(10 seconds,20 seconds),
        EzpayExec.authoriseRedirect.pause(30 seconds, 60 seconds),
        EzpayExec.redirectUrl.pause(30 seconds, 60 seconds),
        EzpayExec.get_detail.pause(30 seconds, 60 seconds),
        EzpayExec.confirmRedirect.pause(30 seconds, 60 seconds)
      )
  }

  val return_one_customer = { scenarioName: String =>
    scenario("FlightSellingScenario-return_one_customer")
      .feed(csv("scenarioFeeders/FlightSellingScenario/return_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult.pause(3 seconds, 8 seconds),
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct.pause(5 seconds, 10 seconds),
        BookingCustomerExec.addCustomers.pause(1 seconds, 3 seconds),
        BookingContactExec.addContact.pause(1 seconds, 5 seconds),
        BookingExec.makeReservation.pause(2 seconds,10 seconds),
        EzpayExec.authoriseRedirect.pause(1 seconds, 10 seconds),
        EzpayExec.redirectUrl.pause(1 seconds, 10 seconds),
        EzpayExec.get_detail.pause(1 seconds, 10 seconds),
        EzpayExec.confirmRedirect.pause(1 seconds, 10 seconds)
      )
  }

  val international_oneway_one_customer = { scenarioName: String =>
    scenario("FlightSellingScenario-international_oneway_one_customer")
      .feed(csv("scenarioFeeders/FlightSellingScenario/international_oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login.pause(3 second,10 seconds),
        FlightExec.createFlightSearch.pause(10 second,20 seconds),
        FlightExec.getFlightSearchResult.pause(5 seconds, 8 seconds),
        BookingExec.createBooking.pause(5 seconds, 8 seconds),
        BookingProductExec.addFlightProduct.pause(5 seconds, 10 seconds),
        BookingCustomerExec.internationalAddCustomers.pause(10 seconds, 20 seconds),
        BookingContactExec.addContact.pause(10 seconds, 20 seconds),
        BookingExec.makeReservation.pause(10 seconds,20 seconds),
        EzpayExec.authoriseRedirect.pause(30 seconds, 60 seconds),
        EzpayExec.redirectUrl.pause(30 seconds, 60 seconds),
        EzpayExec.get_detail.pause(30 seconds, 60 seconds),
        EzpayExec.confirmRedirect.pause(30 seconds, 60 seconds)
      )
  }

  val international_return_one_customer = { scenarioName: String =>
    scenario("FlightSellingScenario-international_return_one_customer")
      .feed(csv("scenarioFeeders/FlightSellingScenario/international_return_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult.pause(3 seconds, 8 seconds),
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct.pause(5 seconds, 10 seconds),
        BookingCustomerExec.internationalAddCustomers.pause(1 seconds, 3 seconds),
        BookingContactExec.addContact.pause(1 seconds, 5 seconds),
        BookingExec.makeReservation.pause(2 seconds,10 seconds),
        EzpayExec.authoriseRedirect.pause(1 seconds, 10 seconds),
        EzpayExec.redirectUrl.pause(1 seconds, 10 seconds),
        EzpayExec.get_detail.pause(1 seconds, 10 seconds),
        EzpayExec.confirmRedirect.pause(1 seconds, 10 seconds)
      )
  }

}
