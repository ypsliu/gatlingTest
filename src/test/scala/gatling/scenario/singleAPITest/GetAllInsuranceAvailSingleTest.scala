package gatling.scenario.singleAPITest

import gatling.exec.InsuranceExec
import io.gatling.core.Predef._

object GetAllInsuranceAvailSingleTest {
  val getAllInsuranceAvail = { scenarioName: String =>
    scenario("getAllInsuranceAvail")
      .exec(
        InsuranceExec.getAllInsuranceAvailSingleTest
      )
}
}
