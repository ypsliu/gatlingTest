package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object CreateFlightSearchCollectionData {
  val createData = { scenarioName: String =>
    scenario("CreateData")
      .exec(
        AuthenticationExec.loginB,
        CollectDataExec.addItem(Array("User-Token"))
      )
}
}
