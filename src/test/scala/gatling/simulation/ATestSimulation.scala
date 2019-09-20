package gatling.simulation

import gatling.protocal.Protocols
import gatling.scenario._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class ATestSimulation extends Simulation {
  setUp(
    AllAncillariesScenario.allAncillaries("Simple Test")
    //通过
    //BookingFreeSeatScenario.free_seat("Simple Test")
    //SwitchSellFreeSeatScenario.free_seat_switchsell("Simple Test")
    //SwitchSellPaymentBaggageScenario.payment_baggage_switchsell("Simple Test")
    //SwitchSellPaymentSeatScenario.payment_seat_switchsell("Simple Test")
    //OMNIFreeSeatScenario.free_seat_omni("Simple Test")

    //FixedDateFlightSearchScenario.oneway_one_customer("Simple Test")
    //FixedDateFlightSearchScenario.return_one_customer("Simple Test")
    //FixedDateFlightSearchScenario.internal_oneway_customer("Simple Test")
    //FixedDateFlightSearchScenario.internal_return_customer("Simple Test")
    //UnFixedDateFlightSearchScenario.oneway_one_customer("Simple Test")
    //UnFixedDateFlightSearchScenario.return_one_customer("Simple Test")
    //UnFixedDateFlightSearchScenario.internal_one_customer("Simple Test")
    //UnFixedDateFlightSearchScenario.internal_return_customer("Simple Test")
    //BookingHistoryScenario.recent_booking("Simple Test")
    //BookingOrderScenario.oneway_one_customer("Simple Test")
    //LocationTestScenario.allLocationTest("Simple Test")

    //main_release能跑通  performance_testing跑不通  怀疑是 xids
    //FlightHotelPackagePaymentScenario.oneway_one_customer("Simple Test")
    //FlightHotelPackagePaymentScenario.return_one_customer("Simple Test")
    //FlightHotelPackagePaymentScenario.internal_one_customer("Simple Test")
    //FlightHotelPackagePaymentScenario.internal_return_customer("Simple Test")

    //FlightHotelPackageBookingScenario.international_oneway_one_customer("Simple Test")

    //出现旅客ID不能为空
    //BookingPaymentSeatScenario.payment_seat("Simple Test")
    //OMNIPaymentSeatScenario.payment_seat_omni("Simple Test")
    //BookingPaymentBaggageScenario.payment_baggage("Simple Test")
    //OMNIPaymentBaggageScenario.payment_baggage_omni("Simple Test")
      .inject(
        //nothingFor(2 seconds), // 1
        atOnceUsers(1), // 2
      ).protocols(Protocols.testServer)
  )
}
