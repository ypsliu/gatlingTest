package gatling.simulation

import gatling.protocal.Protocols
import gatling.scenario.singleAPITest.CreateBookingSingleTest
import io.gatling.core.Predef.{Simulation, nothingFor, _}

import scala.concurrent.duration._

class SingleTestSimulation  extends Simulation {
  setUp(
    CreateBookingSingleTest.createBooking("Simple Test")
      .inject(
        nothingFor(2 seconds), // 1
        constantUsersPerSec(50) during(20 seconds)
      ).protocols(Protocols.testServer)
  )
}
