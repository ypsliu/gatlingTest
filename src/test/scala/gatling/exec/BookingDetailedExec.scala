package gatling.exec

import gatling.java.common.RequestBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object BookingDetailedExec{

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

  //需要传参bookingReference
  val makeReservation = tryMax(1) {

    exec(
      http("makeReservation")
        .post("/bookings/${bookingId}/reservation")
        .headers(header_token)
        .check(status.is(201))
        .check(jsonPath("$.bookingId").saveAs("bookingId"))
        .check(jsonPath("$.bookingReference").saveAs("bookingReference")))
  }.exitHereIfFailed

  val makeReservationSingleTest = tryMax(1) {
    feed(csv("test.csv").batch.circular)
        .exec(
          http("makeReservation")
            .post("/bookings/${bookingId}/reservation")
            .header("User-Token","${User-Token}")
            .check(status.is(201))
            .check(jsonPath("$.bookingId").saveAs("bookingId"))
            .check(jsonPath("$.bookingReference").saveAs("bookingReference")))
  }.exitHereIfFailed

  //需要传参bookingReference
  val getBookingById = tryMax(1) {

      exec(http("getBookingById")
        .get("/bookings/${bookingId}")
        .headers(header_token)
        .check(status.is(200))
        .check(jsonPath("$.flightProducts").saveAs("flightProducts"))
        .check(bodyString.saveAs("response_addPackage"))
        .check(jsonPath("$.flightProducts[0].productId").saveAs("productId"))
      )
  }.exitHereIfFailed

  val getBaggageProductIdByBookingById = tryMax(1) {

      exec(http("getBaggageProductIdByBookingById")
        .get("/bookings/${bookingId}")
        .headers(header_token)
        .check(status.is(200))
        .check(jsonPath("$.flightProducts[0].productId").saveAs("baggageProductId"))
        .check(jsonPath("$.flightProducts[0].productId").saveAs("productId"))
        .check(jsonPath("$").saveAs("products"))
      )
      .exec({ session =>
        session.set("productType","BAGGAGE")
      })
  }.exitHereIfFailed

  val getSeatProductIdByBookingById = tryMax(1) {

      exec(http("getSeatProductIdByBookingById")
        .get("/bookings/${bookingId}")
        .headers(header_token)
        .check(status.is(200))
        .check(jsonPath("$.flightProducts[0].productId").saveAs("productId"))
        .check(jsonPath("$.flightProducts[0].reservationId").saveAs("reservationId"))
      )
      .exec({ session =>
        session.set("productType","SEAT")
      })
  }.exitHereIfFailed

  val getCrossSellAvailabilities = tryMax(1) {

      exec(
        http("getCrossSellAvailabilities")
          .get("/bookings/${bookingId}/products/${productId}/flight/crossSell/availability")
          .headers(header_token)
          .check(status.is(200))
          .check(jsonPath("$").saveAs("sellAvailResult"))
      )
      .exec({ session =>
        println("****************************getCrossSellAvailabilities available start********************************************************")
        println("available : " + session("sellAvailResult").as[String])
        println("****************************getCrossSellAvailabilities available end********************************************************")
        session.set("url", url).set("headers", headers).set("body", body)
      })
  }.exitHereIfFailed
}
