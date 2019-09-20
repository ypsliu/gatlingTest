package gatling.scenario.singleAPITest

import gatling.exec._
import io.gatling.core.Predef._
import scala.concurrent.duration._

object GetBookingByIdSingleTest {
  val getBookingById = { scenarioName: String =>
    scenario("getBookingById")
      .exec(
        BookingExec.getBookingByIdSingleTest
      )
}

  val getBookingByIdFourMPOnewayFiveCustomers = { scenarioName: String =>
    scenario("FourMPOnewayFiveCustomersScenario-fourMPOnewayFiveCustomers")
      .feed(csv("test.csv").batch.circular)
      .exec(
         //免费餐食
//        BookingExec.getFlightProductsByBookingId,
//        AncillariesExec.createAncillaryAvail.pause(10),
//        AncillariesExec.getAncillaryAvail,
//        BookingProductExec.addSpecialRequests,
        // 保险
        BookingExec.getFlightProductsByBookingId,
        InsuranceExec.getAllInsuranceAvail,
        BookingProductExec.addInsuranceProduct,
        BookingProductExec.addInsuranceProductCustomer2,
        // 付费行李
        BookingDetailedExec.getBaggageProductIdByBookingById.pause(1 seconds, 3 seconds),
        FlightBaggageExec.createBaggageAvail.pause(1 seconds, 3 seconds),
        FlightBaggageExec.getAllBaggageAvail.pause(1 seconds, 3 seconds),
        FlightBaggageExec.addBaggageProduct.pause(1 seconds, 3 seconds),

//                BookingDetailedExec.makeReservation ,
//                BookingHistoryExec.retrieveBooking,
//                BookingDetailedExec.getBookingById

      )
  }
}
