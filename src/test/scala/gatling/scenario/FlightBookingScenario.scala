package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

object FlightBookingScenario {

  val oneway_one_customer = { scenarioName: String =>
    scenario("FlightBookingScenario-oneway_one_customer")
      .feed(csv("scenarioFeeders/FlightBookingScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login.pause(3 seconds, 8 seconds),
        FlightExec.createFlightSearch.pause(10 seconds, 20 seconds),
        FlightExec.getFlightSearchResult.pause(10 seconds, 20 seconds),
        BookingExec.createBooking.pause(5 seconds, 10 seconds),
        BookingProductExec.addFlightProduct.pause(10 seconds, 20 seconds),
        BookingCustomerExec.addCustomers.pause(10 seconds, 20 seconds),
        BookingContactExec.addContact.pause(10 seconds, 20 seconds),
        BookingExec.makeReservation.pause(5 seconds, 10 seconds)
      )
  }

  // 已测试
  val return_one_customer = { scenarioName: String =>
    scenario("FlightBookingScenario-return_one_customer")
      .feed(csv("scenarioFeeders/FlightBookingScenario/return_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login.pause(3 seconds, 8 seconds),
        FlightExec.createFlightSearch.pause(5 seconds, 15 seconds),
        FlightExec.getFlightSearchResult.pause(3 seconds, 15 seconds),
        BookingExec.createBooking.pause(5 seconds, 18 seconds),
        BookingProductExec.addFlightProduct.pause(5 seconds, 15 seconds),
        BookingCustomerExec.addCustomers.pause(5 seconds, 13 seconds),
        BookingContactExec.addContact.pause(11 seconds, 15 seconds),
        BookingExec.makeReservation
      )
  }

  // 已测试
  val international_oneway_one_customer = { scenarioName: String =>
    scenario("FlightBookingScenario-international_oneway_one_customer")
      .feed(csv("scenarioFeeders/FlightBookingScenario/international_oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login.pause(3 seconds, 8 seconds),
        FlightExec.createFlightSearch.pause(5 seconds, 15 seconds),
        FlightExec.getFlightSearchResult.pause(3 seconds, 15 seconds),
        BookingExec.createBooking.pause(5 seconds, 18 seconds),
        BookingProductExec.addFlightProduct.pause(5 seconds, 15 seconds),
        BookingCustomerExec.internationalAddCustomers.pause(5 seconds, 13 seconds),
        BookingContactExec.addContact.pause(11 seconds, 15 seconds),
        BookingExec.makeReservation
      )
  }

  // 已测试
  val international_return_one_customer = { scenarioName: String =>
    scenario("FlightBookingScenario-international_return_one_customer")
      .feed(csv("scenarioFeeders/FlightBookingScenario/international_return_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login.pause(3 seconds, 8 seconds),
        FlightExec.createFlightSearchReturn.pause(5 seconds, 15 seconds),
        FlightExec.getFlightSearchResult.pause(3 seconds, 15 seconds),
        BookingExec.createBooking.pause(5 seconds, 18 seconds),
        BookingProductExec.addFlightProduct.pause(5 seconds, 15 seconds),
        BookingCustomerExec.internationalAddCustomers.pause(5 seconds, 13 seconds),
        BookingContactExec.addContact.pause(11 seconds, 15 seconds),
        BookingExec.makeReservation
      )
  }

  val international_return_five_customer = { scenarioName: String =>
    scenario("FlightBookingScenario-international_return_five_customer")
      .feed(csv("scenarioFeeders/FlightBookingScenario/international_return_five_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login.pause(3 seconds, 8 seconds),
        FlightExec.createFlightSearchReturn.pause(5 seconds, 15 seconds),
        FlightExec.getFlightSearchResult.pause(3 seconds, 15 seconds),
        BookingExec.createBooking.pause(5 seconds, 18 seconds),
        BookingProductExec.addFlightProduct.pause(5 seconds, 15 seconds),
        BookingCustomerExec.internationalAddFiveCustomers.pause(5 seconds, 13 seconds),
        BookingContactExec.addContact.pause(11 seconds, 15 seconds),
        BookingExec.makeReservation
      )
  }

}
