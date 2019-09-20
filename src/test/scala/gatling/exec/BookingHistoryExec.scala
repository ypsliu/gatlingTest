package gatling.exec


import gatling.java.common.{RequestBase, Tools}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object BookingHistoryExec{

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

  val createHistoricBookingSummaries = tryMax(1) {

    exec({session=>
      val startDate = Tools.getLocalDate(0)
      val endDate =  Tools.getLocalDate(5)
      session.set("startDate",startDate)
        .set("endDate",endDate)
    }
    )
    .exec(http("createHistoricBookingSummaries")
        .post("/bookingHistory/resultSets")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingHistoryExec/createHistoricBookingSummaries.json")).asJson
        .check(status.is(200))
        .check(jsonPath("$.id").saveAs("resultSetId"))
      )
  }.exitHereIfFailed

  val createSpecialHistoricBookingSummaries = tryMax(1) {

    exec({session=>
      val localStartDate = Tools.getLocalDate(-2)
      val localEndDate =  Tools.getLocalDate(0)
      session.set("localStartDate",localStartDate)
        .set("localEndDate",localEndDate)
    }
    )
      .exec(http("createHistoricBookingSummaries1")
        .post("/bookingHistory/resultSets")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingHistoryExec/createSpecialHistoricBookingSummaries.json")).asJson
        .check(status.is(200))
        .check(jsonPath("$.id").saveAs("resultSetId"))
      )
  }.exitHereIfFailed

  val getHistoricBookingSummaries = tryMax(1) {

      exec(http("getHistoricBookingSummaries")
        .get("/bookingHistory/resultSets/${resultSetId}")
        .headers(header_token)
        .check(status.is(200))
      )
  }.exitHereIfFailed

  val getHistoricBookingSpecialSummaries = tryMax(1) {

      exec(http("getHistoricBookingSummaries")
        .get("/bookingHistory/resultSets/${resultSetId}")
        .headers(header_token)
        .check(status.is(200))
      )
  }.exitHereIfFailed

  val retrieveBooking = tryMax(1) {

      exec(http("retrieveBooking")
        .post("/bookingHistory/retrieval")
        .header("User-Token", "${User-Token}")
        .body(ElFileBody("execFeeders/BookingHistoryExec/retrieveBookingNoBookingId.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.bookingId").saveAs("bookingId"))
      )
  }.exitHereIfFailed

  val retrieveBookingNoBookingId = tryMax(1) {

      exec(http("retrieveBooking")
        .post("/bookingHistory/retrieval")
        .header("User-Token", "${User-Token}")
        .body(ElFileBody("execFeeders/BookingHistoryExec/retrieveBookingNoBookingId.json")).asJson
        .check(status.is(201))
      )
  }.exitHereIfFailed

}