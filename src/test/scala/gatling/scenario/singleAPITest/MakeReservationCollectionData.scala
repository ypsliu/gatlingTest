package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object MakeReservationCollectionData {
  val createData = { scenarioName: String =>
    scenario("CreateData")
      .exec(
        AuthenticationExec.loginB,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        CollectDataExec.addItem(Array("User-Token","bookingId"))
      )
}

  val createDataInternationalReturnFiveCustomers = { scenarioName: String =>
    scenario("CreateData")
      .feed(csv("scenarioFeeders/FlightBookingScenario/international_return_five_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.loginB,
        FlightExec.createFlightSearchReturn,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.internationalAddFiveCustomers,
        BookingContactExec.addContact,
        CollectDataExec.addItem(Array("User-Token","bookingId"))
      )
  }
}
