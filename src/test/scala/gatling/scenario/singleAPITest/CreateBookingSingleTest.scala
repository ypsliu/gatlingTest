package gatling.scenario.singleAPITest

import gatling.exec.BookingExec
import io.gatling.core.Predef._

object CreateBookingSingleTest {
  val createBooking = { scenarioName: String =>
    scenario("CreateBookingSingleTest-createBooking")
      .exec(
        BookingExec.createBookingSingleTest
      )
  }
}
