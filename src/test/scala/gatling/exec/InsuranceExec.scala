package gatling.exec

import gatling.java.common.RequestBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object InsuranceExec{ //extends ExecBase{

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

  val getAllInsuranceAvail = tryMax(1){

    exec(http("getAllInsuranceAvail")
      .get("/bookings/${bookingId}/products/${productId}/crossSell/insurance")
      .headers(header_token)
      .check(status.is(200))
      .check(jsonPath("$.id").saveAs("resultSetId"))
      .check(jsonPath("$.insuranceOptions[0].id").saveAs("resultId"))
    )}.exitHereIfFailed

  val getAllInsuranceAvailSingleTest = tryMax(1){
    feed(csv("test.csv").batch.circular)
        .exec(http("getAllInsuranceAvail")
          .get("/bookings/${bookingId}/products/${productId}/crossSell/insurance")
          .header("User-Token","${User-Token}")
          .check(status.is(200))
          .check(jsonPath("$.id").saveAs("resultSetId"))
          .check(jsonPath("$.insuranceOptions[0].id").saveAs("resultId"))
        )}.exitHereIfFailed
}
