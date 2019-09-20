package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object CreateFlightSearchSingleTest {
  val createFlightSearch = { scenarioName: String =>
    scenario("createFlightSearch")
      .feed(csv("scenarioFeeders/FixedDateFlightSearchScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        FlightExec.createFlightSearchSingleTest
      )
}
}
