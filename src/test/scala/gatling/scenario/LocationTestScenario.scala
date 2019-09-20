package gatling.scenario

import gatling.exec.{EzpayExec, _}
import io.gatling.core.Predef.{csv, scenario, _}

import scala.concurrent.duration._

object LocationTestScenario {

  val allLocationTest = { scenarioName: String =>
    scenario("LocationTestScenario-test")
      .feed(csv("scenarioFeeders/location/getLocations.csv").batch.circular)
      .feed(csv("scenarioFeeders/location/getChildrenLocations.csv").batch.circular)
      .feed(csv("scenarioFeeders/location/GetClosestCityLocation.csv").batch.circular)
      .exec(
        LocationExec.getLocations,
        LocationExec.getParentLocations,
        LocationExec.getChildrenLocations,
        LocationExec.getLocationByCode,
        LocationExec.getClosestCityLocation,
        LocationExec.getAirportLocations
      )
  }

  val test = { scenarioName: String =>
    scenario("LocationTestScenario-test")
      .feed(csv("scenarioFeeders/location/getLocations.csv").batch.circular)
      .exec(
        LocationExec.getLocations
      )
  }

  val getParentLocations = { scenarioName: String =>
    scenario("LocationTestScenario-getParentLocations")
      .feed(csv("scenarioFeeders/location/GetParentLocations.csv").batch.circular)
      .exec(
        LocationExec.getParentLocations
      )
  }

  val getChildrenLocations = { scenarioName: String =>
    scenario("LocationTestScenario-getChildrenLocations")
      .feed(csv("scenarioFeeders/location/getChildrenLocations.csv").batch.circular)
      .exec(
        LocationExec.getChildrenLocations
      )
  }

  val getLocationByCode = { scenarioName: String =>
    scenario("LocationTestScenario-getLocationByCode")
      .feed(csv("scenarioFeeders/location/GetParentLocations.csv").batch.circular)
      .exec(
        LocationExec.getLocationByCode
      )
  }

  val getClosestCityLocation = { scenarioName: String =>
    scenario("LocationTestScenario-getClosestCityLocation")
      .feed(csv("scenarioFeeders/location/GetClosestCityLocation.csv").batch.circular)
      .exec(
        LocationExec.getClosestCityLocation
      )
  }

  val getAirportLocations = { scenarioName: String =>
    scenario("LocationTestScenario-getAirportLocations")
      .exec(
        LocationExec.getAirportLocations
      )
  }

}
