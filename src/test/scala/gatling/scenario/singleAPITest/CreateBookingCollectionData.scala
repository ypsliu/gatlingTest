package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object CreateBookingCollectionData {
  val createData = { scenarioName: String =>
    scenario("CreateData")
      .exec(
        AuthenticationExec.loginB,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        CollectDataExec.addItem(Array("User-Token"))
      )
}
}
