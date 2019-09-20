package gatling.simulation

import gatling.protocal.Protocols
import gatling.scenario.BookingPaymentSeatScenario
import io.gatling.core.Predef.{atOnceUsers, nothingFor, _}

import scala.concurrent.duration._

class BookingPaymentSeatSimulation extends Simulation{

  setUp(
    BookingPaymentSeatScenario.payment_seat("Simple Test")
      .inject(
        nothingFor(2 seconds), // 1
        atOnceUsers(1), // 2
      ).protocols(Protocols.testServer)
  )
}
