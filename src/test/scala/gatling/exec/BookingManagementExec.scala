package gatling.exec

import gatling.java.common.RequestBase
import io.gatling.core.Predef.{exec, tryMax, _}
import io.gatling.http.Predef.{http, status, _}

object BookingManagementExec{

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

  val cancelUnpaidBookingItems = tryMax(1) {

    exec(
      http("cancelUnpaidBookingItems")
        .post("/bookings/${bookingId}/cancellation/unpaid")
        .header("User-Token","${User-Token}")
        .check(status.is(202))
//        .check(jsonPath("$.reference").saveAs("reference"))
    )
  }.exitHereIfFailed

  val createRefundRemarks = tryMax(1) {
    exec(
      http("createRefundRemarks")
        .post("/bookings/${bookingId}/refundRemarks")
        .header("User-Token","${User-Token}")
        .body(ElFileBody("execFeeders/BookingManagementExec/createRefundRemarks.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.refundRemarksId").saveAs("refundRemarksId"))
    )
  }.exitHereIfFailed

  val createPartialFlightCancellationCost = tryMax(1) {
    exec(
      http("createPartialFlightCancellationCost")
        .post("/bookings/${bookingId}/products/${productId}/flight/partial/cost/cancellation")
        .header("User-Token","${User-Token}")
        .body(ElFileBody("execFeeders/BookingManagementExec/createPartialFlightCancellationCost.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.cancellationCostId").saveAs("cancellationCostId"))
    )
  }.exitHereIfFailed

  val cancelBookingPartialFlight = tryMax(1) {
    exec(
      http("cancelBookingPartialFlight")
        .post("/bookings/${bookingId}/products/${productId}/flight/partial/cancellation")
        .header("User-Token","${User-Token}")
        .body(ElFileBody("execFeeders/BookingManagementExec/cancelBookingPartialFlight.json")).asJson
        .check(status.is(202))
    )
  }.exitHereIfFailed
}
