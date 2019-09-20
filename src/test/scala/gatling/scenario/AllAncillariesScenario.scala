package gatling.scenario

import gatling.common.SeatFreeChargeTypeEnum._
import gatling.exec._
import io.gatling.core.Predef._

import scala.concurrent.duration._

object AllAncillariesScenario {
  //订单管理中附加服务免费座位购买
  val allAncillaries = { scenarioName: String =>
    scenario("AllAncillariesScenario-all_ancillaries")
      //要免费座位或者付费座位请修改这里
      .feed(seat_type.random)
      .feed(csv("scenarioFeeders/AllAncillariesScenario/oneway_one_customer.csv").batch.circular)
      .exec(
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking.pause(10, 30),
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingDetailedExec.makeReservation
      )
//      .exec(
//      // 免费餐食
//      BookingExec.getFlightProductsByBookingId,
//      BookingDetailedExec.getCrossSellAvailabilities,
//      AncillariesExec.createAncillaryAvail.pause(10),
//      AncillariesExec.getAncillaryAvail,
//      BookingProductExec.addSpecialRequests
//    )
//    // /*.exec(
//
//      BookingExec.getFlightProductsByBookingId,
//      InsuranceExec.getAllInsuranceAvail,
//      BookingProductExec.addInsuranceProduct
//    )*/ .exec(
//      //付费行李
//      BookingDetailedExec.getBaggageProductIdByBookingById.pause(1 seconds, 3 seconds),
//      FlightBaggageExec.createBaggageAvail.pause(1 seconds, 3 seconds),
//      FlightBaggageExec.getAllBaggageAvail.pause(1 seconds, 3 seconds),
//      FlightBaggageExec.addBaggageProduct.pause(1 seconds, 3 seconds)
//    )
      .exec(
      //交钱
//      BookingExec.makeReservation,
      EzpayExec.authoriseRedirect,
      EzpayExec.redirectUrl,
      EzpayExec.get_detail,
      EzpayExec.confirmRedirect
    )
//      .exec(
//      //购买座位
//      FlightExec.createFlightSearch,
//      FlightExec.getFlightSearchResult,
//      BookingExec.createBooking.pause(10, 30),
//      BookingProductExec.addFlightProduct,
//      BookingCustomerExec.addCustomers,
//      BookingContactExec.addContact,
//      BookingDetailedExec.makeReservation,
//      BookingDetailedExec.getSeatProductIdByBookingById.pause(3 seconds, 5 seconds),
//      PaymentSeatExec.getSeatMap,
//      PaymentSeatExec.assignSeats
//    )
      .doIf(session => session("chargeType").as[String].endsWith("AVAILABLE")) {
        exec(
          //如果是付费行李，那么还得交钱
          BookingExec.makeReservation,
          EzpayExec.authoriseRedirect.pause(1 seconds, 3 seconds),
          EzpayExec.redirectUrl.pause(1 seconds, 3 seconds),
          EzpayExec.get_detail.pause(1 seconds, 3 seconds),
          EzpayExec.confirmRedirect.pause(1 seconds, 3 seconds)
        )
      }
  }
}
