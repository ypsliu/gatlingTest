package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._

object AddInsuranceProductCollectionData {
  val createData = { scenarioName: String =>
    scenario("CreateData")
      .feed(csv("scenarioFeeders/FixedDateFlightSearchScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.loginB,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingDetailedExec.makeReservation,
        BookingExec.getFlightProductsByBookingId,
        InsuranceExec.getAllInsuranceAvail,
        CollectDataExec.addItem(Array("User-Token","bookingId","productId","resultSetId"))
      )
}
}
