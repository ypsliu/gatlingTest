package gatling.simulation

import gatling.protocal.Protocols
import gatling.scenario._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class LocationTestSimulation extends Simulation {
  setUp(
    LocationTestScenario.allLocationTest("Simple Test")
      .inject(
        nothingFor(2 seconds), // 1
        atOnceUsers(1), // 2
      ).protocols(Protocols.testLocation)
  )
}
