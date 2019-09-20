package gatling.scenario.singleAPITest

import gatling.exec.BookingProductExec
import io.gatling.core.Predef._

object AddFlightProductSingleTest {
  val addFlightProduct = { scenarioName: String =>
    scenario("addFlightProduct")
      .exec(
        BookingProductExec.addFlightProductSingleTest
      )
}
}
