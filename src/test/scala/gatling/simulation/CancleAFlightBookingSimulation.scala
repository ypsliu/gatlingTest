package gatling.simulation

import gatling.protocal.Protocols
import gatling.scenario.CancelaFlightBookingScenario
import io.gatling.core.Predef.{Simulation, nothingFor, _}

import scala.concurrent.duration._

class CancleAFlightBookingSimulation extends Simulation {
  setUp(
    CancelaFlightBookingScenario.cancelFlight("Simple Test")
      .inject(
        nothingFor(2 seconds), // 1
        rampUsers(1)during(5 seconds), // 2
      ).protocols(Protocols.testServer)
  )
}
