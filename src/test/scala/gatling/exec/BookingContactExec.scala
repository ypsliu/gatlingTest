package gatling.exec

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import gatling.java.common.RequestBase


object BookingContactExec{

  var rq: RequestBase = null
  var body: String = null
  var url: String = null
  var headers = Map.empty[String, String]

  val header_token= Map(
    "User-Token" -> "${User-Token}"
  )
  val header_refer= Map(
    "Referer" -> "http://119.254.234.92:9280/easypay/"
  )
  val test_json= Map(
    "bookingId" -> "${bookingId}"
  )

  val addContact =tryMax(1){
    exec(http("addContact")
      .post("/bookings/${bookingId}/contact")
      .headers(header_token)
      .body(ElFileBody("execFeeders/BookingContactExec/addContact.json")).asJson
      .check(status.is(204))
    )
  }.exitHereIfFailed


  //修改登记人信息
  val updateContact = tryMax(1){

    exec(
      http("updateContact")
        .put("/bookings/${bookingId}/contact")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingContactExec/updateContact.json")).asJson
        .check(status.is(204))
    )
  }.exitHereIfFailed
}
