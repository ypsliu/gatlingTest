package gatling.exec

import gatling.java.common.RequestBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.io._

import gatling.common.CommonUtils

import scala.concurrent.duration._

object AuthenticationExec {//extends ExecBase {
  val header_token= Map(
    "User-Token" -> "${User-Token}"
  )
  val header_refer= Map(
    "Referer" -> "http://119.254.234.92:9280/easypay/"
  )
  val test_json= Map(
    "bookingId" -> "${bookingId}"
  )

  val login = tryMax(1){
    feed(csv("execFeeders/login.csv").batch.circular)
      .exec(
        http("login") //"Thread.currentThread().getStackTrace()(2).getMethodName())
          .post("/authentication")
          //.headers("${headers}".asInstanceOf[mutable.Map[String,String]].toMap)
          .body(ElFileBody("execFeeders/AuthenticationExec/login.json")).asJson
          .check(status.is(201))
          .check(jsonPath("$.token").saveAs("User-Token"))
//          .check(jsonPath("$.token").saveAs("userIDs"))
      )
//      .exec({session=>
//      CommonUtils.maps.put(session("userIDs").as[String],session("userIDs").as[String])
//        session
//    })
  }.exitHereIfFailed

  val loginB = tryMax(1) {

    exec(
      http("login") //"Thread.currentThread().getStackTrace()(2).getMethodName())
        .post("/authentication")
        //.headers("${headers}".asInstanceOf[mutable.Map[String,String]].toMap)
        .body(ElFileBody("execFeeders/AuthenticationExec/loginB.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.token").saveAs("User-Token")))
//      .exec({session=>
//        CommonUtils.maps.put(session("User-Token").as[String],session("User-Token").as[String])
//        CommonUtils.lists.add(session("User-Token").as[String])
//        session
//      })
  }.exitHereIfFailed

  val sendMobileAuthentication = tryMax(1) {
    feed(csv("execFeeders/mobileAuthentication.csv").batch.circular)
      .exec(
      http("sendMobileAuthentication")
        .post("/mfa/mobile")
        .body(ElFileBody("execFeeders/AuthenticationExec/sendMobileAuthentication.json")).asJson
        .check(status.is(201)))
  }.exitHereIfFailed

  val retrieveExternalFlightBookings = tryMax(1) {
    feed(csv("execFeeders/mobileAuthentication.csv").batch.circular)
      .feed(csv("scenarioFeeders/FlightBookingScenario/oneway_one_customer.csv").batch.circular)
      .exec(//??????????
        AuthenticationExec.login,
        FlightExec.createFlightSearch,
        FlightExec.getFlightSearchResult,
        BookingExec.createBooking,
        BookingProductExec.addFlightProduct,
        BookingCustomerExec.addCustomers,
        BookingContactExec.addContact,
        BookingExec.makeReservation,
        BookingHistoryExec.retrieveBooking,
        BookingExec.getFlightProductsByBookingId,
        EzpayExec.authoriseRedirect,
        EzpayExec.redirectUrl,
        EzpayExec.get_detail,
        EzpayExec.confirmRedirect.pause(30 seconds, 90 seconds),
        BookingExec.getFlightTicketNumberByOrderSuccess
      )
      .exec(
      http("retrieveExternalFlightBookings")
        .post("/external/bookings/flights/retrieval")
        .headers(header_token)
        .body(ElFileBody("execFeeders/AuthenticationExec/retrieveExternalFlightBooking.json")).asJson
        .check(status.is(200))
        .check(jsonPath("$.id").saveAs("resultSetId"))
        .check(jsonPath("$.externalFlightBookings[0].reservationId").saveAs("reservationId")))
  }.exitHereIfFailed
}
