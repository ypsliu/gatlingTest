package gatling.exec

import com.alibaba.fastjson._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object BookingExec{
  val header_token= Map(
    "User-Token" -> "${User-Token}"
  )
    val createBooking = tryMax(1){
      exec(http("createBooking")
        .post("/bookings")
        .headers(header_token)
        .check(status.is(201))
        .check(jsonPath("$.bookingId").saveAs("bookingId"))
      )
  }.exitHereIfFailed

  val createBookingSingleTest = tryMax(1){
    feed(csv("test.csv").batch.circular)
    .exec(http("createBooking")
      .post("/bookings")
      .header("User-Token","${User-Token}")
      .check(status.is(201))
      .check(jsonPath("$.bookingId").saveAs("bookingId"))
    )
  }.exitHereIfFailed


  // 添加一个 bookingReference
  val makeReservation = tryMax(1){
      exec(http("makeReservation")
        .post("/bookings/${bookingId}/reservation")
        .headers(header_token)
        .check(status.is(201),jsonPath("$.bookingId").saveAs("bookingId"),
        jsonPath("$.bookingReference").saveAs("bookingReference"))
      )}.exitHereIfFailed

  val getBookingById = tryMax(1){

      exec(http("getBookingById")
        .get("/bookings/${bookingId}")
        .header("User-Token","${User-Token}")
        .check(status.is(200))
        .check(jsonPath("$.packageProducts").saveAs("packageProducts"))
      )
      .exec({session =>
        val packageProducts = JSON.parseArray(session("packageProducts").as[String])
        var flightproductId = 0
        var hotelproductId = 0

        for(i <- 0 until packageProducts.size()){
          flightproductId = packageProducts.get(i).asInstanceOf[JSONObject].get("flightProducts").asInstanceOf[JSONArray].get(0).asInstanceOf[JSONObject].get("productId").asInstanceOf[Int]
          hotelproductId = packageProducts.get(i).asInstanceOf[JSONObject].get("hotelProducts").asInstanceOf[JSONArray].get(0).asInstanceOf[JSONObject].get("productId").asInstanceOf[Int]
        }
        session.remove("packageProducts").setAll("flightproductId" -> flightproductId, "hotelproductId" -> hotelproductId)
      })}.exitHereIfFailed

  val getBookingByIdSingleTest = tryMax(1){
    feed(csv("test.csv").batch.circular)
        .exec(http("getBookingById")
          .get("/bookings/${bookingId}")
          .header("User-Token","${User-Token}")
          .check(status.is(200))
        )}.exitHereIfFailed

  /**
    * getBookingById check bookStatus and additionalBookingStatus
    */
  val getBookingByIdCheckBookStatus = tryMax(1) {

      exec(http("getBookingByIdCheckBookStatus")
        .get("/bookings/${bookingId}")
        .header("User-Token", "${User-Token}")
        .check(status.is(200))
        .check(jsonPath("$").saveAs("bookings"))
      )
      .exec({ session =>
        val bookings =JSON.parseObject(session("bookings").as[String])
        val bookingStatus = bookings.get("bookingStatus").asInstanceOf[String]
        val additionalBookStatus = bookings.get("additionalBookingStatus").asInstanceOf[String]
        var productId = 0
        if("BOOKED".equals(bookingStatus) && "TICKET_SUCCESS".equals(additionalBookStatus)){
          println("payments status is : TICKET_SUCCESS ")
          productId = bookings.get("flightProducts").asInstanceOf[JSONArray].get(0).asInstanceOf[JSONObject].get("productId").asInstanceOf[Integer];
        }
        session.remove("bookings").set("productId", productId)
      })

  }.exitHereIfFailed

  /**
    * getBookingById  for flightProducts
    */
  val getFlightProductsByBookingId = tryMax(1) {

      exec(http("getFlightProductsByBookingId")
        .get("/bookings/${bookingId}")
        .header("User-Token", "${User-Token}")
        .check(status.is(200))
        .check(jsonPath("$.flightProducts").saveAs("flightProducts"))
      )
      .exec({ session =>
        val flightProducts =JSON.parseArray(session("flightProducts").as[String])
        val productId = flightProducts.get(0).asInstanceOf[JSONObject].get("productId").asInstanceOf[Integer]
        session.remove("flightProducts").set("mealProductId",productId).set("productId",productId)
      })

  }.exitHereIfFailed

  /**
    * getBookingById for packageProducts
    */
  val getPackageProductsByBookingId = tryMax(1) {

      exec(http("getPackageProductsByBookingId")
        .get("/bookings/${bookingId}")
        .header("User-Token", "${User-Token}")
        .check(status.is(200), jsonPath("$.packageProducts").saveAs("packageProducts"))
      )
      .exec({ session =>
        val packageProducts =JSON.parseArray(session("packageProducts").as[String])
        val flightProductId = packageProducts.get(0).asInstanceOf[JSONObject].get("flightProducts")
          .asInstanceOf[JSONArray].get(0).asInstanceOf[JSONObject].get("productId").asInstanceOf[Integer]
        val hotelProductId = packageProducts.get(0).asInstanceOf[JSONObject].get("hotelProducts")
          .asInstanceOf[JSONArray].get(0).asInstanceOf[JSONObject].get("productId").asInstanceOf[Integer]
        session.remove("packageProducts").set("flightProductId",flightProductId).set("hotelProductId",hotelProductId)
      })

  }.exitHereIfFailed

  val createBookingWithExternalFlightBooking = tryMax(1){

      exec(http("createBookingWithExternalFlightBooking")
        .post("/external/bookings/flights/import")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingExec/createBookingWithExternalFlightBooking.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.bookingId").saveAs("bookingId"))
      )}.exitHereIfFailed

  var getFlightTicketNumberByOrderSuccess = tryMax(1) {

      exec(http("getFlightTicketNumberByOrderSuccess")
        .get("/bookings/${bookingId}")
        .headers(header_token)
        .check(status.is(200), jsonPath("$.flightProducts").saveAs("flightProducts"))
      )
      .exec({ session =>
        var flightProducts = JSON.parseArray(session("flightProducts").as[String])
        var flightTicketNumber = flightProducts.getJSONObject(0).getJSONArray("tickets").getJSONObject(0).getString("ticketNumber")
        session.remove("flightProducts").set("flightTicketNumber", flightTicketNumber)
      })

  }.exitHereIfFailed
}
