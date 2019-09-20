package gatling.simulation

import gatling.scenario.UnFixedDateFlightSearchScenario
import io.gatling.core.Predef.{Simulation, atOnceUsers, nothingFor}
import gatling.protocal.Protocols
import io.gatling.core.Predef._

import scala.concurrent.duration._

class UnFixedDateFlightSearchSimulation  extends Simulation{
  setUp(
    UnFixedDateFlightSearchScenario.oneway_one_customer("Simple Test")
      .inject(
        nothingFor(2 seconds), // 1
        atOnceUsers(1), // 2
      ).protocols(Protocols.testServer)
  )
}
