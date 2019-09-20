package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._
import scala.concurrent.duration._

object UnFixedDateFlightSearchScenario {

  val oneway_one_customer = { scenarioName: String =>
    scenario("UnFixedDateFlightSearchScenario-oneway_one_customer")
      .feed(csv("scenarioFeeders/UnFixedDateFlightSearchScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        FlightCalendarExec.getCalendarSearch
      )
  }

  val return_one_customer = { scenarioName: String =>
    scenario("UnFixedDateFlightSearchScenario-return_one_customer")
      .feed(csv("scenarioFeeders/UnFixedDateFlightSearchScenario/return_one_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        FlightCalendarExec.getCalendarSearch
      )
  }

  val internal_one_customer = { scenarioName: String =>
    scenario("UnFixedDateFlightSearchScenario-internal_one_customer")
      .feed(csv("scenarioFeeders/UnFixedDateFlightSearchScenario/internal_one_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        FlightCalendarExec.getCalendarSearch
      )
  }

  val internal_return_customer = { scenarioName: String =>
    scenario("UnFixedDateFlightSearchScenario-internal_return_customer")
      .feed(csv("scenarioFeeders/UnFixedDateFlightSearchScenario/internal_return_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        FlightCalendarExec.getCalendarSearch
      )
  }
}
