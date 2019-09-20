package gatling.scenario

import gatling.exec._
import io.gatling.core.Predef._
import scala.concurrent.duration._
import gatling.common.SeatFreeChargeTypeEnum._

object OMNIPaymentSeatScenario {

  //全渠道附加服务付费座位购买  OMNI
  val payment_seat_omni = { scenarioName: String =>
    scenario("OMNIPaymentSeatScenario-payment_seat_omni")
      .feed(seat_charge_type)
      .exec(
        //复制参数
        //AuthenticationExec.sendMobileAuthentication.pause(1 seconds, 3 seconds),
        AuthenticationExec.loginB.pause(1 seconds, 3 seconds),
        //注意：此方法包含造其他订单出票信息
        AuthenticationExec.retrieveExternalFlightBookings.pause(1 seconds, 3 seconds),
        BookingExec.createBookingWithExternalFlightBooking.pause(1 seconds, 3 seconds),
        BookingContactExec.updateContact.pause(1 seconds, 3 seconds),
        // getBookingById  有改动
        BookingDetailedExec.getSeatProductIdByBookingById.pause(3 seconds, 5 seconds),
        //有问题  需要接入环境跑下，确认数据
        PaymentSeatExec.getSeatMap,
        PaymentSeatExec.assignSeats,
        BookingExec.makeReservation.pause(1 seconds, 3 seconds),
        EzpayExec.authoriseRedirect.pause(1 seconds, 3 seconds),
        EzpayExec.redirectUrl.pause(1 seconds, 3 seconds),
        EzpayExec.get_detail.pause(1 seconds, 3 seconds),
        EzpayExec.confirmRedirect.pause(1 seconds, 3 seconds),
        ConfirmCostFreeSeatExec.confirmCostFreeSeat,
        BookingHistoryExec.retrieveBooking,
        BookingDetailedExec.getBookingById
      )
  }
}
