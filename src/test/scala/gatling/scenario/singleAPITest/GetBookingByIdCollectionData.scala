package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._
import scala.concurrent.duration._


object GetBookingByIdCollectionData {
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
        CollectDataExec.addItem(Array("User-Token","bookingId"))
      )
}

  val createDataFourMPOnewayFiveCustomers = { scenarioName: String =>
    scenario("FourMPOnewayFiveCustomersScenario-fourMPOnewayFiveCustomers")
      .feed(csv("scenarioFeeders/BookingDetailScenario/fourMPOnewayFiveCustomers.csv").batch.circular)
      .exec(
        AuthenticationExec.loginB,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking.pause(10,30),
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addFiveCustomers,
        BookingContactExec.addContact,
        BookingDetailedExec.makeReservation,
        // 免费餐食
        BookingExec.getFlightProductsByBookingId,
        AncillariesExec.createAncillaryAvail.pause(10),
        AncillariesExec.getAncillaryAvail,
        BookingProductExec.addSpecialRequests,
        BookingDetailedExec.makeReservation ,
//        // 保险
        BookingExec.getFlightProductsByBookingId,
        InsuranceExec.getAllInsuranceAvail,
        BookingProductExec.addInsuranceProduct,
        BookingDetailedExec.makeReservation ,
//        // 付费行李
        BookingDetailedExec.getBaggageProductIdByBookingById.pause(1 seconds, 3 seconds),
        FlightBaggageExec.createBaggageAvail.pause(1 seconds, 3 seconds),
        FlightBaggageExec.getAllBaggageAvail.pause(1 seconds, 3 seconds),
        FlightBaggageExec.addBaggageProduct.pause(1 seconds, 3 seconds),
        BookingDetailedExec.makeReservation ,
        CollectDataExec.addItem(Array("User-Token","bookingId"))
//
//        BookingDetailedExec.makeReservation ,
//        BookingHistoryExec.retrieveBooking,
//        BookingDetailedExec.getBookingById

      )
  }
}
