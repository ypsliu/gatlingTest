package gatling.exec

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import gatling.java.common.RequestBase

object BookingProductExec{

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

  val addFlightProduct = tryMax(1){
    exec(http("addFlightProduct")
        .post("/bookings/${bookingId}/products/flight")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingProductExec/addFlightProduct.json")).asJson
        .check(status.is(201), jsonPath("$.productId").saveAs("productId"))
      )}.exitHereIfFailed

  val addFlightProductSingleTest = tryMax(1){
    feed(csv("test.csv").batch.circular)
    .exec(http("addFlightProduct")
      .post("/bookings/${bookingId}/products/flight")
      .header("User-Token","${User-Token}")
      .body(ElFileBody("execFeeders/BookingProductExec/addFlightProduct.json")).asJson
      .check(status.is(201), jsonPath("$.productId").saveAs("productId"))
    )}.exitHereIfFailed

  val createProductSwitchSellSearch = tryMax(1){

      exec(http("createProductSwitchSellSearch")
        .post("/bookings/${bookingId}/products/${productId}/switchSell/package")
        .headers(header_token)
        .check(status.is(201), jsonPath("$.id").saveAs("package_resultSetId"))
      )}.exitHereIfFailed

  val addAncillaryProduct = tryMax(1){

     exec(http("addAncillaryProduct")
        .post("/bookings/${bookingId}/products/ancillary")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingProductExec/addAncillaryProduct.json")).asJson
        .check(status.is(201))
      )}.exitHereIfFailed

  val addInsuranceProduct = tryMax(1){

      exec(http("addInsuranceProduct")
        .post("/bookings/${bookingId}/products/insurance")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingProductExec/addInsuranceProduct.json")).asJson
        .check(status.is(201), jsonPath("$.bookingProducts[0].productId").saveAs("productId"))
      )}.exitHereIfFailed

  val addInsuranceProductCustomer2 = tryMax(1){

    exec(http("addInsuranceProduct")
      .post("/bookings/${bookingId}/products/insurance")
      .headers(header_token)
      .body(ElFileBody("execFeeders/BookingProductExec/addInsuranceProductCustomer2.json")).asJson
      .check(status.is(201), jsonPath("$.bookingProducts[0].productId").saveAs("productId"))
    )}.exitHereIfFailed

  val addInsuranceProductSingleTest = tryMax(1){
    feed(csv("test.csv").batch.circular)
      .exec(http("addInsuranceProduct")
        .post("/bookings/${bookingId}/products/insurance")
        .header("User-Token","${User-Token}")
        .body(ElFileBody("execFeeders/BookingProductExec/addInsuranceProduct.json")).asJson
        .check(status.is(201), jsonPath("$.bookingProducts[0].productId").saveAs("productId"))
      )}.exitHereIfFailed

  val addSpecialRequests = tryMax(1){

    exec(http("addSpecialRequests")
      .post("/bookings/${bookingId}/products/${mealProductId}/flight/specialRequests")
      .headers(header_token)
      .body(ElFileBody("execFeeders/BookingProductExec/addSpecialRequests.json")).asJson
      .check(status.is(201))
    )}.exitHereIfFailed

  val addSpecialRequestsSingleTest = tryMax(1){
    feed(csv("test.csv").batch.circular)
        .exec(http("addSpecialRequests")
          .post("/bookings/${bookingId}/products/${mealProductId}/flight/specialRequests")
          .header("User-Token","${User-Token}")
          .body(ElFileBody("execFeeders/BookingProductExec/addSpecialRequests.json")).asJson
          .check(status.is(201))
        )}.exitHereIfFailed
}
