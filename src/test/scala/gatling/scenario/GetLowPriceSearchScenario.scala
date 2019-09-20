package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._


object GetLowPriceSearchScenario{

  // 3

  val getLowPriceSearch = { scenarioName: String =>
      scenario("GetLowPriceSearchScenario")
      .exec(
        FlightCacheExec.getLowPriceSearch
    )
  }
}
