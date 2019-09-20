package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._

import scala.concurrent.duration._

object FlightHotelPackageBookingScenario {

  // 9 未测试

  val oneway_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackageBookingScenario-oneway_one_customer")
      .feed(csv("scenarioFeeders/FlightHotelPackageBookingScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        PackageExec.createPackageSearch,
        PackageExec.getPackageSearchResults,
        BookingExec.createBooking,
        BookingPackageExec.addPackage,
        BookingExec.getPackageProductsByBookingId,
        BookingCustomerExec.addPackagesCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation

      )
  }

  val return_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackageBookingScenario-return_one_customer")
      .feed(csv("scenarioFeeders/FlightHotelPackageBookingScenario/return_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        PackageExec.createPackageSearch,
        PackageExec.getPackageSearchResults,
        BookingExec.createBooking,
        BookingPackageExec.addPackage,
        BookingExec.getPackageProductsByBookingId,
        BookingCustomerExec.addPackagesCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation

      )
  }

  val international_oneway_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackageBookingScenario-international_oneway_one_customer")
      .feed(csv("scenarioFeeders/FlightHotelPackageBookingScenario/international_oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        PackageExec.createPackageSearch,
        PackageExec.getPackageSearchResults,
        BookingExec.createBooking,
        BookingPackageExec.addPackage,
        BookingExec.getPackageProductsByBookingId,
        BookingCustomerExec.internationalAddPackagesCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation

      )
  }

  val international_return_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackageBookingScenario-international_return_one_customer")
      .feed(csv("scenarioFeeders/FlightHotelPackageBookingScenario/international_return_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        PackageExec.createPackageSearch,
        PackageExec.getPackageSearchResults,
        BookingExec.createBooking,
        BookingPackageExec.addPackage,
        BookingExec.getPackageProductsByBookingId,
        BookingCustomerExec.internationalAddPackagesCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation

      )
  }
}
