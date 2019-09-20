package gatling.exec

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import gatling.common.CommonUtils
import gatling.java.common.Tools
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

import scala.util.control.Breaks._

object FlightExec{
  val header_token= Map(
    "User-Token" -> "${User-Token}"
  )
  val createFlightSearch = repeat(5){
    exec({session=>
      val departureDate = Tools.getLocalDate(10)
      session.set("departureDate",departureDate)
    }
    )
    .doIfOrElse("${User-Token.isUndefined()}"){exec(http("createFlightSearch")
      .post("/flight/resultSets")
      .body(ElFileBody("execFeeders/FlightExec/createFlightSearch.json")).asJson
      .check(status.is(201))
      .check(jsonPath("$.id").saveAs("resultSetId")))
    }{exec(http("createFlightSearch")
      .post("/flight/resultSets")
      .headers(header_token)
      .body(ElFileBody("execFeeders/FlightExec/createFlightSearch.json")).asJson
      .check(status.is(201))
      .check(jsonPath("$.id").saveAs("resultSetId")))

    }
  }.exitHereIfFailed.pause(5 seconds, 15 seconds)

  val createFlightSearchReturn = repeat(CommonUtils.getRadomInt.intValue()){
    exec({session=>
      val departureDate = Tools.getLocalDate(10)
      val departureDateReurn = Tools.getLocalDate(20)
      session.set("departureDate",departureDate).set("departureDateReurn",departureDateReurn)
    }
    )
      .doIfOrElse("${User-Token.isUndefined()}"){exec(http("createFlightSearchReturn")
        .post("/flight/resultSets")
        .body(ElFileBody("execFeeders/FlightExec/createFlightSearchReturn.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.id").saveAs("resultSetId")))
      }{exec(http("createFlightSearchReturn")
        .post("/flight/resultSets")
        .headers(header_token)
        .body(ElFileBody("execFeeders/FlightExec/createFlightSearchReturn.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.id").saveAs("resultSetId")))

      }
  }.exitHereIfFailed

  val createFlightSearchSingleTest = repeat(CommonUtils.getRadomInt.intValue()){
    feed(csv("test.csv").batch.circular)
      .exec({session=>
        val departureDate = Tools.getLocalDate(4)
        session.set("departureDate",departureDate)
      }
      )
      .exec(http("createFlightSearch")
        .post("/flight/resultSets")
        .header("User-Token","${User-Token}")
        .body(ElFileBody("execFeeders/FlightExec/createFlightSearch.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.id").saveAs("resultSetId")))
  }.exitHereIfFailed

  val getFlightSearchResult = tryMax(1) {
    doIfOrElse("${User-Token.isUndefined()}") {
      exec(http("getFlightSearchResult")
        .get("/flight/resultSets/${resultSetId}")
        .check(status.is(200))
        .check(jsonPath("$.flightOptions[0].id").saveAs("flightId"))
        .check(jsonPath("$.flightOptions[0].prices[0].id").saveAs("priceId")))
    } {
      exec(http("getFlightSearchResult")
        .get("/flight/resultSets/${resultSetId}")
        .headers(header_token)
        .check(status.is(200))
        .check(jsonPath("$.flightOptions[0].id").saveAs("flightId"))
        .check(jsonPath("$.flightOptions[0].prices[0].id").saveAs("priceId")))
    }}.exitHereIfFailed

  //?????  ????30
  //  val createFlightSearch = repeat(CommonUtils.getRadomInt.intValue()){
  //    exec({session=>
  //      rq = new CreateFlightSearchRq()
  //      url = rq.asInstanceOf[CreateFlightSearchRq].getUrl()
  //      //headers = rq.asInstanceOf[CreateFlightSearchRq].getHeader(session("User-Token").as[String]).asScala.toMap
  //      body = rq.asInstanceOf[CreateFlightSearchRq].getBody(session("departureDate").as[String],session("destination").as[String],session("origin").as[String],
  //        session("cabinClass").as[String],session("passengerCounts").as[String],session("flightType").as[String],session("returnDate").as[String])
  //      //saveRqParas(session)
  //      session.set("url",url).set("headers",headers).set("body",body)
  //    })
  //      .doIfOrElse("${User-Token.isUndefined()}"){exec(http("createFlightSearch")
  //        .post("${url}")
  //        .body(StringBody("${body}")).asJson
  //        .check(status.is(201))
  //        .check(jsonPath("$.id").saveAs("resultSetId")))
  //
  //      }{exec(http("createFlightSearch")
  //        .post("${url}")
  //        .headers(header_token)
  //        .body(StringBody("${body}")).asJson
  //        .check(status.is(201))
  //        .check(jsonPath("$.id").saveAs("resultSetId")))
  //
  //      }.pause(3,8)}.exitHereIfFailed
  //
  //  val getFlightSearchResult = tryMax(1) {
  //    exec({ session =>
  //      rq = new GetFlightSearchResultRq()
  //      url = rq.asInstanceOf[GetFlightSearchResultRq].getUrl(session("resultSetId").as[String])
  //      //headers = rq.asInstanceOf[GetFlightSearchResultRq].getHeader(session("User-Token").as[String]).asScala.toMap
  //      //saveRqParas(session)
  //      session.set("url", url).set("headers", headers).set("body", body)
  //    })
  //      .doIfOrElse("${User-Token.isUndefined()}") {
  //        exec(http("getFlightSearchResult")
  //          .get("${url}")
  //          .check(status.is(200))
  //          .check(jsonPath("$.flightOptions[0].id").saveAs("flightId"))
  //          .check(jsonPath("$.flightOptions[0].prices[0].id").saveAs("priceId")))
  //      } {
  //        exec(http("getFlightSearchResult")
  //          .get("${url}")
  //          .headers(header_token)
  //          .check(status.is(200))
  //          .check(jsonPath("$.flightOptions[0].id").saveAs("flightId"))
  //          .check(jsonPath("$.flightOptions[0].prices[0].id").saveAs("priceId")))
  //      }}.exitHereIfFailed

  val getFlightSearchResultId = tryMax(1) {

    exec(http("getFlightSearchResultId")
      .get("/flight/resultSets/${resultSetId}")
      .headers(header_token)
      .check(status.is(200))
      .check(jsonPath("$.id").saveAs("flightSearchResultID"))
      .check(jsonPath("$.flightOptions").saveAs("flightOptions")))
      .exec({session =>
        val resultSetId = session("flightSearchResultID").as[String]
        var priceId,resultId=0

        val flightOptions = JSON.parseArray(session("flightOptions").as[String])
        breakable(
          for (i <- 0 until flightOptions.size()){
            val flightOption = flightOptions.get(i).asInstanceOf[JSONObject]
            val prices = flightOption.get("prices").asInstanceOf[JSONArray]
            for (j <- 0 until prices.size()){
              val price = prices.get(j).asInstanceOf[JSONObject]
              val fareFamilyCode = price.get("fareFamilyCode").asInstanceOf[String]
              if("FULLPRICE".equals(fareFamilyCode)){
                priceId = price.get("id").asInstanceOf[Integer]
                resultId = flightOption.get("id").asInstanceOf[Integer]
                break()
              }
            }
          }
        )

        println("priceId is : "+priceId+" , resultId is : "+resultId)
        session.remove("flightOptions").remove("flightSearchResultID")
          .set("priceId", priceId).set("flightId", resultId).set("resultSetId", resultSetId)
      })
  }.exitHereIfFailed
}