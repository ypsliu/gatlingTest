package gatling.test



import gatling.protocal.Protocols
import gatling.scenario._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class LogInTest extends Simulation {
  setUp(
    LoginScenario.login("test").inject(constantUsersPerSec(100) during(10 seconds))
  ).protocols(Protocols.testServer)
}
