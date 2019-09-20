package gatling.simulation

import gatling.protocal.Protocols
import gatling.scenario.RefundFlightScenario
import io.gatling.core.Predef.{Simulation, nothingFor, _}

import scala.concurrent.duration._

class RefundFlightSimulation extends Simulation {
  setUp(
    RefundFlightScenario.refundFlight("Simple Test")
      .inject(
        nothingFor(2 seconds), // 1
        rampUsers(1)during(5 seconds), // 2
      ).protocols(Protocols.testServer)
  )
}
