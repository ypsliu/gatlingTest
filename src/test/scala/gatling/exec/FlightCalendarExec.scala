package gatling.exec

import gatling.common.ExecBase
import gatling.java.common.{RequestBase, Tools}
import gatling.java.flightCalendar.PostFlightCalendarSearchRq
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object FlightCalendarExec{

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

  //由于非固定日期查询是从缓存取数据，接口一个即可
  val getCalendarSearch = tryMax(1) {
//    exec({ session =>
//      rq = new PostFlightCalendarSearchRq()
//      url = rq.getUrl
//      body = rq.asInstanceOf[PostFlightCalendarSearchRq].getBody(
//        session("currentCode").as[String], session("departureDate").as[String], session("destination").as[String],
//        session("origin").as[String], session("cabinClass").as[String], session("passengerCounts").as[String],
//        session("flightType").as[String], session("returnDate").as[String]
//      )
//      session.set("url", url).set("headers", headers).set("body", body)
//    })
     exec({ session =>
       val departureDate = Tools.getTodayDate
       session.set("departureDate",departureDate)
     }
     )
      .doIfOrElse("${User-Token.isUndefined()}") {
        exec(
          http("getCalendarSearch")
            .post("/flight/calendar")
            .body(ElFileBody("execFeeders/FlightCalendarExec/getCalendarSearch.json")).asJson
            .check(status.is(201))
        )
      } {
        exec(http("getCalendarSearch")
          .post("/flight/calendar")
          .headers(header_token)
          .body(ElFileBody("execFeeders/FlightCalendarExec/getCalendarSearch.json")).asJson
          .check(status.is(201)))
      }
  }.exitHereIfFailed
}
