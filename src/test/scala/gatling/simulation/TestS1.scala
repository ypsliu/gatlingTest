package gatling.simulation

import gatling.protocal.Protocols
import gatling.scenario._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class TestS1 extends Simulation {
  setUp(
    //FixedDateFlightSearchScenario.return_one_customer("Simple Test").inject(atOnceUsers(1)),
    //AllAncillariesScenario.allAncillaries("Simple Test").inject(atOnceUsers(1)),
    LoginScenario.login("111").inject(constantUsersPerSec(100) during(10 seconds))
    //AllAncillariesScenario.allAncillaries("Simple Test").inject(atOnceUsers(1)),
    //FlightBookingScenario.oneway_one_customer("Simple Test").inject(atOnceUsers(1)),
    //FlightBookingScenario.oneway_one_customer("Simple Test").inject(rampUsers(23000) during (60 minutes)),
    //FlightSellingScenario.oneway_one_customer("Simple Test").inject(rampUsers(10) during (1 seconds)),
    //FixedDateFlightSearchScenario.oneway_one_customer("Simple Test").inject(rampUsers(50000) during (60 minutes)),
    //UnFixedDateFlightSearchScenario.oneway_one_customer("Simple Test").inject(rampUsers(50000) during (60 minutes)),
    //LocationTestScenario.allLocationTest("location").inject(constantUsersPerSec(3) during(100 minutes))
//    FlightHotelPackageBookingScenario.oneway_one_customer("Simple Test").inject(atOnceUsers(1)),
//    BookingHistoryScenario.recent_booking("Simple Test").inject(atOnceUsers(1)),
//    BookingOrderScenario.oneway_one_customer("Simple Test").inject(atOnceUsers(1)),
//    PayTheOrderScenario.oneway_one_customer("Simple Test").inject(atOnceUsers(1)),
//    SwitchSellHotelScenario.oneway_one_customer("Simple Test").inject(atOnceUsers(1)),
//    SwitchSellPaymentSeatScenario.pay_paymentseat("Simple Test").inject(atOnceUsers(1)),
//    SwitchSellPaymentBaggageScenario.pay_paymentbaggage("Simple Test").inject(atOnceUsers(1)),
//    SwitchSellChargeableMealPurchaseScenario.oneway_one_customer("Simple Test").inject(atOnceUsers(1)),
  ).protocols(Protocols.testServer)
}
