package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object MakeReservationSingleTest {
  val makeReservation = { scenarioName: String =>
    scenario("makeReservation")
      .exec(
        BookingDetailedExec.makeReservationSingleTest
      )
}
}
