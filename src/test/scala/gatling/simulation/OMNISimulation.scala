package gatling.simulation

import gatling.protocal.Protocols
import gatling.scenario._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class OMNISimulation extends Simulation{
  setUp(
    OMNIPaymentBaggageScenario.payment_baggage_omni("Simple Test")
      .inject(
        nothingFor(1 seconds), // 1
        atOnceUsers(1), // 2
      ).protocols(Protocols.testServer)
  )
}
