package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._

object FlightHotelPackagePaymentScenario {

  val oneway_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackagePaymentScenario-oneway_one_customer")
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
        BookingExec.makeReservation,

        BookingHistoryExec.retrieveBooking,
        BookingExec.getBookingById,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect
      )
  }

  val return_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackagePaymentScenario-return_one_customer")
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
        BookingExec.makeReservation,

        BookingHistoryExec.retrieveBooking,
        BookingExec.getBookingById,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect
      )
  }

  val internal_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackagePaymentScenario-internal_one_customer")
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
        BookingExec.makeReservation,

        BookingHistoryExec.retrieveBooking,
        BookingExec.getBookingById,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect
      )
  }

  val internal_return_customer = { scenarioName: String =>
    scenario("FlightHotelPackagePaymentScenario-internal_return_customer")
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
        BookingExec.makeReservation,

        BookingHistoryExec.retrieveBooking,
        BookingExec.getBookingById,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect
      )
  }
}
