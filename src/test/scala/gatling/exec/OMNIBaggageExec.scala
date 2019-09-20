package gatling.exec

import gatling.java.booking.GetBookingByIdRq
import gatling.java.common.RequestBase
import io.gatling.core.Predef.{StringBody, csv, exec, feed, jsonPath, tryMax, _}
import io.gatling.http.Predef.{http, status, _}

import scala.collection.JavaConverters._

object OMNIBaggageExec{

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
  var st: Int = 200

val createBookingWithExternalFlightBooking = tryMax(1) {
    feed(csv("execFeeders/mobileAuthentication.csv").batch.circular)
      .exec(
        http("createBookingWithExternalFlightBooking") //"Thread.currentThread().getStackTrace()(2).getMethodName())
          .post("/external/bookings/flights/retrieval")
          //.headers("${headers}".asInstanceOf[mutable.Map[String,String]].toMap)
          .header("User-Token","${User-Token}")
          .body(ElFileBody("execFeeders/OMNIBaggageExec/createBookingWithExternalFlightBooking.json")).asJson
          .check(status.is(201))
          .check(jsonPath("$.resultSetId").saveAs("resultSetId"))
          .check(jsonPath("$.externalFlightBookings[0].reservationId").saveAs("reservationId"))
      )
  }.exitHereIfFailed

  //需要传参bookingReference
  val getBookingById = tryMax(1){
    exec({session=>
      rq = new GetBookingByIdRq()
      url = rq.asInstanceOf[GetBookingByIdRq].getUrl(session("bookingId").as[String])
      headers = rq.asInstanceOf[GetBookingByIdRq].getHeader(session("User-Token").as[String]).asScala.toMap
      session.set("url",url).set("headers",headers).set("body",body)
    })
      .exec(http("getBookingById")
        .get("/bookings/${bookingId}")
        .header("User-Token","${User-Token}")
        .check(status.is(200))
        .check(jsonPath("$.flightProducts[0].productId").saveAs("mealProductId"))
      )
      .exec({session =>
        session.set("url",url).set("headers",headers).set("body",body)
      })
  }.exitHereIfFailed
}
