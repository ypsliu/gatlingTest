package gatling.exec

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import gatling.java.common.RequestBase

object BookingCustomerExec{
  val header_token= Map(
    "User-Token" -> "${User-Token}"
  )

  val addCustomers = tryMax(1){
      exec({session=>
        val docId = "GD" + (111111 + (Math.random * 88888)).toInt
        session.set("docId",docId)
    })
      .exec(http("addCustomers")
        .post("/bookings/${bookingId}/customers")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingCustomerExec/addCustomers.json")).asJson
        .check(status.is(201))
      )}.exitHereIfFailed

  val addFiveCustomers = tryMax(1){
    exec({session=>
      val docId1 = "GD" + (111111 + (Math.random * 88888)).toInt
      val docId2 = "GD" + (111111 + (Math.random * 88888)).toInt
      val docId3 = "GD" + (111111 + (Math.random * 88888)).toInt
      val docId4 = "GD" + (111111 + (Math.random * 88888)).toInt
      val docId5 = "GD" + (111111 + (Math.random * 88888)).toInt
      session.set("docId1",docId1).set("docId2",docId2).set("docId3",docId3).set("docId4",docId4).set("docId5",docId5)
    })
      .exec(http("addCustomers")
        .post("/bookings/${bookingId}/customers")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingCustomerExec/addFiveCustomers.json")).asJson
        .check(status.is(201))
      )}.exitHereIfFailed

  val internationalAddCustomers = tryMax(1){
    exec({session=>
      val docId = "GD" + (111111 + (Math.random * 88888)).toInt
      session.set("docId",docId)
    })
      .exec(http("internationalAddCustomers")
        .post("/bookings/${bookingId}/customers")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingCustomerExec/internationalAddCustomers.json")).asJson
        .check(status.is(201)))
  }.exitHereIfFailed

  val internationalAddFiveCustomers = tryMax(1){
    exec({session=>
      val docId1 = "GD" + (111111 + (Math.random * 88888)).toInt
      val docId2 = "GD" + (111111 + (Math.random * 88888)).toInt
      val docId3 = "GD" + (111111 + (Math.random * 88888)).toInt
      val docId4 = "GD" + (111111 + (Math.random * 88888)).toInt
      val docId5 = "GD" + (111111 + (Math.random * 88888)).toInt
      session.set("docId1",docId1).set("docId2",docId2).set("docId3",docId3).set("docId4",docId4).set("docId5",docId5)
    })
      .exec(http("internationalAddFiveCustomers")
        .post("/bookings/${bookingId}/customers")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingCustomerExec/internationalAddFiveCustomers.json")).asJson
        .check(status.is(201)))
  }.exitHereIfFailed


  // 增加 packages
  val addPackagesCustomers = tryMax(1){

      exec(http("addCustomers")
        .post("/bookings/${bookingId}/customers")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingCustomerExec/addPackagesCustomers.json")).asJson
        .check(status.is(201))
      )}.exitHereIfFailed

  // 增加 packages international
  val internationalAddPackagesCustomers = tryMax(1){

      exec(http("addCustomers")
        .post("/bookings/${bookingId}/customers")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingCustomerExec/internationalAddPackagesCustomers.json")).asJson
        .check(status.is(201))
      )}.exitHereIfFailed
}
