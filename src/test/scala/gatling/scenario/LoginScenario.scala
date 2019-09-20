package gatling.scenario

import gatling.exec.AuthenticationExec
import io.gatling.core.Predef.scenario

object LoginScenario {

  val login = { scenarioName: String =>
    scenario("LoginScenario-login")
      .exec(
        AuthenticationExec.login
      )
  }

}
