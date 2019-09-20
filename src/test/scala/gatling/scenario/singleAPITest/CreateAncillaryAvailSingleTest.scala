package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object CreateAncillaryAvailSingleTest {
  val createAncillaryAvail = { scenarioName: String =>
    scenario("createAncillaryAvail")
      .exec(
        AncillariesExec.createAncillaryAvailSingleTest
      )
}
}
