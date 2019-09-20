package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._

object FlightHotelPackageSearchScenario {

  val oneway_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackageSearchScenario-oneway_one_customer")
      .feed(csv("scenarioFeeders/FlightHotelPackageBookingScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        PackageExec.createPackageSearch,
        PackageExec.getPackageSearchResults
      )
  }

  val return_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackageSearchScenario-return_one_customer")
      .feed(csv("scenarioFeeders/FlightHotelPackageBookingScenario/return_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        PackageExec.createPackageSearch,
        PackageExec.getPackageSearchResults
      )
  }

  val internal_one_customer = { scenarioName: String =>
    scenario("FlightHotelPackageSearchScenario-internal_one_customer")
      .feed(csv("scenarioFeeders/FlightHotelPackageBookingScenario/international_oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        PackageExec.createPackageSearch,
        PackageExec.getPackageSearchResults
      )
  }

  val internal_return_customer = { scenarioName: String =>
    scenario("FlightHotelPackageSearchScenario-internal_return_customer")
      .feed(csv("scenarioFeeders/FlightHotelPackageBookingScenario/international_return_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        PackageExec.createPackageSearch,
        PackageExec.getPackageSearchResults
      )
  }
}
