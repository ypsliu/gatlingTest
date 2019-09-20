package gatling.scenario.singleAPITest

import io.gatling.core.Predef._
import gatling.exec._

object AddInsuranceProductSingleTest {
  val addInsuranceProduct = { scenarioName: String =>
    scenario("addInsuranceProduct")
      .exec(
        BookingProductExec.addInsuranceProductSingleTest
      )
}
}
