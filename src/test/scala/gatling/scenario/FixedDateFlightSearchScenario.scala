package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object FixedDateFlightSearchScenario {
  val oneway_one_customer = { scenarioName: String =>
    scenario("FixedDateFlightSearchScenario-oneway_one_customer")
      .feed(csv("scenarioFeeders/FixedDateFlightSearchScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.login,
        FlightExec.createFlightSearch.pause(1 seconds, 8 seconds)//,
        //FlightExec.getFlightSearchResult
      )
  }

  val return_one_customer = { scenarioName: String =>
    scenario("FixedDateFlightSearchScenario-return_one_customer")
      .feed(csv("scenarioFeeders/FixedDateFlightSearchScenario/return_one_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.login,
        FlightExec.createFlightSearchReturn.pause(1 seconds, 3 seconds)//,
        //FlightExec.getFlightSearchResult
      )
  }

  val internal_oneway_customer = { scenarioName: String =>
    scenario("FixedDateFlightSearchScenario-internal_oneway_customer")
      .feed(csv("scenarioFeeders/FixedDateFlightSearchScenario/internal_oneway_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.login,
        FlightExec.createFlightSearch.pause(1 seconds, 8 seconds),
        FlightExec.getFlightSearchResult
      )
  }

  val internal_return_customer = { scenarioName: String =>
    scenario("FixedDateFlightSearchScenario-internal_return_customer")
      .feed(csv("scenarioFeeders/FixedDateFlightSearchScenario/internal_return_customer.csv").batch.circular)
      .exec(
        //AuthenticationExec.login,
        FlightExec.createFlightSearch.pause(1 seconds, 8 seconds),
        FlightExec.getFlightSearchResult
      )
  }
}
