package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._

import scala.concurrent.duration._

object LoginScenario {

  val login = { scenarioName: String =>
    scenario("LoginScenario-login")
      .exec(
        AuthenticationExec.login
      )
  }
}
