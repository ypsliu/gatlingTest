package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object AddFlightProductCollectionData {
  val createData = { scenarioName: String =>
    scenario("CreateData")
      .feed(csv("scenarioFeeders/FlightBookingScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.loginB,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking,
        CollectDataExec.addItem(Array("User-Token","bookingId","resultSetId","flightId","priceId"))
      )
}
}
