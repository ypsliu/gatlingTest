package gatling.exec

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.math.BigDecimal

import gatling.java.common.RequestBase

object AncillariesExec{

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

  val createAncillaryAvail = tryMax(1){

    exec(
      http("createAncillaryAvail")
        .post("/bookings/${bookingId}/flight/ancillaries/resultSets")
        .headers(header_token)
        .body(ElFileBody("execFeeders/AncillariesExec/createAncillaryAvail.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.id").saveAs("mealResultId")))
  }.exitHereIfFailed

  val createAncillaryAvailSingleTest = tryMax(1){
    feed(csv("test.csv").batch.circular)
        .exec(
          http("createAncillaryAvail")
            .post("/bookings/${bookingId}/flight/ancillaries/resultSets")
            .header("User-Token","${User-Token}")
            .body(ElFileBody("execFeeders/AncillariesExec/createAncillaryAvail.json")).asJson
            .check(status.is(201))
            .check(jsonPath("$.id").saveAs("mealResultId")))
  }.exitHereIfFailed

  val getAncillaryAvail = tryMax(1){

    exec(
      http("getAncillaryAvail")
        .get("/bookings/${bookingId}/flight/ancillaries/resultSets/${mealResultId}")
        .headers(header_token)
        .check(status.is(200))
        .check(jsonPath("$.ancillaryOptions").saveAs("ancillaryOptions"))
    )
      .exec({session=>
        val freemeal_Idlist = new JSONArray()
        val chargeablemeal_Idlist = new JSONArray()
        val lounge_Idlist = new JSONArray()

        val amountBigDecimal = new BigDecimal("0.0");

        val ancillaryOptions = JSON.parseArray(session("ancillaryOptions").as[String])

        for(i <- 0 until ancillaryOptions.size()){
          val ancillaryOption = ancillaryOptions.get(i).asInstanceOf[JSONObject]
          val type_ = ancillaryOption.get("ancillary").asInstanceOf[JSONObject].get("type").asInstanceOf[String]
          val priceBreakdown = ancillaryOption.get("priceBreakdown").asInstanceOf[JSONArray]
          for(j <- 0 until priceBreakdown.size()){
            val prices = priceBreakdown.get(j).asInstanceOf[JSONObject].get("prices").asInstanceOf[JSONArray]
            for(k <- 0 until prices.size()){
              val amount = prices.get(k).asInstanceOf[JSONObject].get("total").asInstanceOf[JSONObject].get("amount").asInstanceOf[BigDecimal]
              val id = ancillaryOption.get("id").asInstanceOf[String]
              if("MEAL".equals(type_)) {
                if(amountBigDecimal.equals(amount)){
                  freemeal_Idlist.add(id)
                }else{
                  chargeablemeal_Idlist.add(id)
                }
              }else if("LOUNGE".equals(type_)){
                lounge_Idlist.add(id)
              }
            }
          }
        }
        var chargeablemealProductId ,freemealProductId ,loungeId= ""
        if (freemeal_Idlist.size() > 0){
          freemealProductId = freemeal_Idlist.get(0).asInstanceOf[String]
        }
        if(chargeablemeal_Idlist.size() > 0){
          chargeablemealProductId = chargeablemeal_Idlist.get(0).asInstanceOf[String]
        }
        if(lounge_Idlist.size() > 0){
          loungeId = lounge_Idlist.get(0).asInstanceOf[String]
        }

        session.set("chargeablemealProductId", chargeablemealProductId).set("freemealProductId",freemealProductId)
      })
  }.exitHereIfFailed

}
