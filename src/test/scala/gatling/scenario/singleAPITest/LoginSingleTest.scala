package gatling.scenario.singleAPITest

import gatling.exec.AuthenticationExec
import io.gatling.core.Predef._

object LoginSingleTest {
  val login = { scenarioName: String =>
    scenario("Login")
      .exec(
        AuthenticationExec.login
      )
}
}
