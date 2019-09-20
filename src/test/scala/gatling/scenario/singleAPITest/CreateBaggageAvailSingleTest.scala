package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object CreateBaggageAvailSingleTest {
  val createBaggageAvail = { scenarioName: String =>
    scenario("createBaggageAvail")
      .exec(
        FlightBaggageExec.createBaggageAvailSingleTest
      )
  }
}
