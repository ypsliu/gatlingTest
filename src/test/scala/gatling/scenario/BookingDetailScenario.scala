package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._
import scala.concurrent.duration._

object BookingDetailScenario {

  //订单管理中订单详情查询  GET /bookings/{bookingId}  getBookingById
  val oneway_one_customer = { scenarioName: String =>
    scenario("BookingOrderScenario-oneway_one_customer")
      .feed(csv("scenarioFeeders/BookingDetailScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult.pause(2 seconds, 3 seconds),
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct.pause(2 seconds, 5 seconds),
        BookingCustomerExec.addCustomers.pause(1 seconds, 3 seconds),
        BookingContactExec.addContact.pause(1 seconds, 3 seconds),
        BookingDetailedExec.makeReservation.pause(1 seconds, 3 seconds),
        BookingHistoryExec.retrieveBooking,
        BookingDetailedExec.getBookingById
      )
  }

  val fourMPOnewayFiveCustomers = { scenarioName: String =>
    scenario("FourMPOnewayFiveCustomersScenario-fourMPOnewayFiveCustomers")
      .feed(csv("scenarioFeeders/BookingDetailScenario/fourMPOnewayFiveCustomers.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
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
        // 保险
        BookingExec.getFlightProductsByBookingId,
        InsuranceExec.getAllInsuranceAvail,
        BookingProductExec.addInsuranceProduct,
        // 付费行李
        BookingDetailedExec.getBaggageProductIdByBookingById.pause(1 seconds, 3 seconds),
        FlightBaggageExec.createBaggageAvail.pause(1 seconds, 3 seconds),
        FlightBaggageExec.getAllBaggageAvail.pause(1 seconds, 3 seconds),
        FlightBaggageExec.addBaggageProduct.pause(1 seconds, 3 seconds),

        BookingDetailedExec.makeReservation ,
        BookingHistoryExec.retrieveBooking,
        BookingDetailedExec.getBookingById

      )
  }
}
