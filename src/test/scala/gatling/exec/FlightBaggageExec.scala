package gatling.exec

import gatling.java.common.RequestBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object FlightBaggageExec{

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

  val createBaggageAvail = tryMax(1){

    exec({session=>
     session.set("packageSearchType","ONEWAY")
    }
    )
      .exec(http("createBaggageAvail")
        .post("/bookings/${bookingId}/flight/baggage/resultSets")
        .headers(header_token)
        .body(ElFileBody("execFeeders/FlightBaggageExec/createBaggageAvail.json")).asJson
        .check(status.is(201), jsonPath("$.id").saveAs("baggageResultId"))
        .check(jsonPath("$.totalResults").saveAs("totalResults"))
      )}.exitHereIfFailed

  val createBaggageAvailSingleTest = tryMax(1){
    feed(csv("test.csv").batch.circular)
        .exec({session=>
          session.set("packageSearchType","ONEWAY")
        })
      .exec(http("createBaggageAvail")
        .post("/bookings/${bookingId}/flight/baggage/resultSets")
        .header("User-Token","${User-Token}")
        .body(ElFileBody("execFeeders/FlightBaggageExec/createBaggageAvail.json")).asJson
        .check(status.is(201), jsonPath("$.id").saveAs("baggageResultId"))
        .check(jsonPath("$.totalResults").saveAs("totalResults"))
      )}.exitHereIfFailed

  val getAllBaggageAvail = tryMax(1){

      exec(http("getAllBaggageAvail")
        .get("/bookings/${bookingId}/flight/baggage/resultSets/${baggageResultId}")
        .headers(header_token)
      )}.exitHereIfFailed

  val addBaggageProduct = tryMax(1){

      exec(http("addBaggageProduct")
        .post("/bookings/${bookingId}/products/baggage")
        .headers(header_token)
        .body(ElFileBody("execFeeders/FlightBaggageExec/addBaggageProduct.json")).asJson
        .check(status.is(201))
      )}.exitHereIfFailed

  val addBaggageProductSingleTest = tryMax(1){
    feed(csv("test.csv").batch.circular)
        .exec(http("addBaggageProduct")
          .post("/bookings/${bookingId}/products/baggage")
          .header("User-Token","${User-Token}")
          .body(ElFileBody("execFeeders/FlightBaggageExec/addBaggageProduct.json")).asJson
          .check(status.is(201))
        )}.exitHereIfFailed
}
