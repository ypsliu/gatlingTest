package gatling.simulation

import gatling.protocal.Protocols
import gatling.scenario.SwitchSellPaymentBaggageScenario
import io.gatling.core.Predef.{Simulation, atOnceUsers, nothingFor, _}

import scala.concurrent.duration._

class SwitchSellPaymentBaggageSimulation extends Simulation{
  setUp(
    SwitchSellPaymentBaggageScenario.payment_baggage_switchsell("Simple Test")
      .inject(
        nothingFor(2 seconds), // 1
        atOnceUsers(1), // 2
        //          rampUsers(30) during (10 seconds),// 3
        //          constantUsersPerSec(3) during(10 seconds), // 4
        //          constantUsersPerSec(5) during(15 seconds) randomized, // 5
        //          rampUsersPerSec(3) to 15 during(1 minutes), // 6
        //          rampUsersPerSec(5) to 10 during(1 minutes) randomized // 7
        //          splitUsers(1000) into(rampUsers(10) during(10 seconds)) separatedBy(10 seconds), // 8
        //          splitUsers(1000) into(rampUsers(10) during(10 seconds)) separatedBy atOnceUsers(30), // 9
        //        heavisideUsers(1000) during (20 seconds)
      ).protocols(Protocols.testServer)
  )
}
