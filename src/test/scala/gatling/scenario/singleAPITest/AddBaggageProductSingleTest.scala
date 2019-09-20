package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object AddBaggageProductSingleTest {
  val addBaggageProduct = { scenarioName: String =>
    scenario("addBaggageProduct")
      .exec(
        FlightBaggageExec.addBaggageProductSingleTest
      )
  }
}
