package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object AddSpecialRequestsSingleTest {
  val addSpecialRequests = { scenarioName: String =>
    scenario("addSpecialRequests")
      .exec(
        BookingProductExec.addSpecialRequestsSingleTest
      )
}
}
