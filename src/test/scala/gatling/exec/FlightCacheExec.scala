package gatling.exec

import gatling.common.{CommonUtils, ExecBase}
import gatling.java.common.{RequestBase, Tools}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object FlightCacheExec{

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

//  //国内场景
//  val getCalendarSearchDomestic = tryMax(1){
//    feed(csv("scenarioFeeders/getLowPriceSearchScenario/getLowPriceSearch.csv").batch.circular)
////    .exec({session=>
////      rq = new GetCalendarSearchRq()
////      url = rq.asInstanceOf[GetCalendarSearchRq].getUrl(session("iataCode").as[String],session("departureDate").as[String],session("returnDate").as[String])
////      session.set("url",url).set("headers",headers).set("body",body)
////    })
//      .exec(http("getCalendarSearchDomestic")
//        .get("/flightCache/calendarSearch?from=${iataCode}&departureDate=${departureDate}&returnDate=${returnDate}")
//        .headers(header_token)
//        .check(status.is(200)) //, bodyString.saveAs("cacheSearchResponse")
//      )}.exitHereIfFailed
//
//  //国际场景
//  val getCalendarSearchInternal = tryMax(1){
//    feed(csv("scenarioFeeders/getLowPriceSearchScenario/getLowPriceSearch.csv").batch.circular)
////      .exec({session=>
////        rq = new GetCalendarSearchRq()
////        url = rq.asInstanceOf[GetCalendarSearchRq].getUrl(session("iataCode").as[String],session("departureDate").as[String],session("returnDate").as[String])
////        session.set("url",url).set("headers",headers).set("body",body)
////      })
//      .exec(http("getCalendarSearchInternal")
//        .get("/flightCache/calendarSearch?from=${iataCode}&departureDate=${departureDate}&returnDate=${returnDate}")
//        .headers(header_token)
//        .check(status.is(200)) //, bodyString.saveAs("cacheSearchResponse")
//      ).exec({ session=>
//      session.set("url",url).set("headers",headers).set("body",body)
//    })}.exitHereIfFailed

  val getLowPriceSearch = repeat(CommonUtils.getRadomInt.intValue()){

    exec({session=>
      val departureDate = Tools.getLocalDate(0)
      val returnDate =  Tools.getLocalDate(5)
      val from = "PEK"
      session.set("departureDate",departureDate)
        .set("returnDate",returnDate)
        .set("from",from)

    })
      .exec(http("getLowPriceSearch")
        .get("/flightCache/lowPriceSearch?from=${from}&departureDate=${departureDate}&returnDate=${returnDate}")
        .check(status.is(200))
      )}.exitHereIfFailed

}
