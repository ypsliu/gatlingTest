package gatling.exec

import gatling.java.common.RequestBase
import io.gatling.core.Predef.{StringBody, csv, exec, feed, jsonPath, tryMax}
import io.gatling.http.Predef.{http, status}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ConfirmCostFreeSeatExec{

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

  val confirmCostFreeSeat = tryMax(1) {

      exec(
        http("confirmCostFreeSeat") //"Thread.currentThread().getStackTrace()(2).getMethodName())
          .post("/bookings/${bookingId}/products/costFreeSeats/confirmation")
          .header("User-Token","${User-Token}")
          .check(status.is(204))
          .check(jsonPath("$.resultSetId").saveAs("resultSetId"))
          .check(jsonPath("$.externalFlightBookings[0].reservationId").saveAs("reservationId"))
      )
  }.exitHereIfFailed
}
