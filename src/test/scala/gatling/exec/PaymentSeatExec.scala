package gatling.exec

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import gatling.java.common.RequestBase
import io.gatling.core.Predef.{StringBody, exec, jsonPath, tryMax, _}
import io.gatling.http.Predef.{http, status, _}

object PaymentSeatExec{

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

  //获取可预定付费座位集合
  def getAvailSeats(cabinsRows: JSONArray, chargeType: String): JSONArray = {
    val availSeats = new JSONArray()
    //查询空座 放到availSeats中，只考虑成人情况
    for (index <- 0 until cabinsRows.size()) {
      var number = cabinsRows.get(index).asInstanceOf[JSONObject].get("number").toString()
      var blocks = JSON.parseArray(cabinsRows.get(index).asInstanceOf[JSONObject].get("blocks").toString())
      for (seatIndex <- 0 until blocks.size()) {
        var seatJOSN = blocks.get(seatIndex).asInstanceOf[JSONObject]
        var seatType = seatJOSN.get("type").toString()
        //压力测试 我只考虑成人情况了，如果是CHD 需要用SEAT_IS_FREE标识判断
        if (seatJOSN.containsKey("availability")) {
          var availability = seatJOSN.get("availability").toString()
          var seatNumber = seatJOSN.get("number")
          var available = seatJOSN.get("available").toString()
          if (chargeType.equals(availability)) {
            var node = JSON.parseObject("{}")
            node.put("rowNumber", number)
            node.put("seatNumber", seatNumber)
            availSeats.add(node)
          }
        }
      }
    }
    return availSeats
  }

  //拼接下一环节assignSeats 座位参数
  def getAvailSeatsParam(customers: JSONArray, cabinsRows: JSONArray, sessionParam: JSONObject,chargeType: String): JSONObject = {

    val availSeats = getAvailSeats(cabinsRows,chargeType)
    var availSeatsParam = JSON.parseObject("{}")
    var seatAssignments = JSON.parseArray("[]")
    val seatMapId = sessionParam.getString("seatMapId")
    val seatMapResponseId = sessionParam.getString("seatMapResponseId")

    for (index <- 0 until customers.size()) {
      var seatAssignment = JSON.parseObject("{}")
      var seatAvail = availSeats.get(index).asInstanceOf[JSONObject]
      var customerId = customers.get(index).asInstanceOf[JSONObject].get("id")
      seatAssignment.put("customerId", customerId)
      seatAssignment.put("seatMapId", seatMapId)
      seatAssignment.put("rowNumber", seatAvail.getString("rowNumber"))
      seatAssignment.put("seatNumber", seatAvail.getString("seatNumber"))
      seatAssignments.add(seatAssignment)
    }
    availSeatsParam.put("seatAssignments", seatAssignments)
    availSeatsParam.put("seatMapResponseId", seatMapResponseId)
    return availSeatsParam
  }

  val getSeatMap = tryMax(1) {

      exec(http("getSeatMap")
        .get("/bookings/${bookingId}/flight/seatMaps?productId=${productId}&flightSegmentId=1")
        .headers(header_token)
        .check(status.is(200))
        .check(jsonPath("$.id").saveAs("seatMapResponseId"))
        .check(jsonPath("$.products[0].seatMaps[0].seatMapId").saveAs("seatMapId"))
        .check(jsonPath("$.products[0].seatMaps[0].cabins[0].rows").saveAs("cabinsRows"))
        .check(jsonPath("$.products[0].customers").saveAs("customers"))
        .check(jsonPath("$.products[0].customers[0].id").saveAs("customerId"))
        .check(jsonPath("$.products[0].seatMaps[0].cabins[0].rows[0].number").saveAs("rowNumber"))
        .check(jsonPath("$.products[0].seatMaps[0].cabins[0].rows[0].blocks[0].number").saveAs("seatNumber"))
      )
      .exec({ session =>
        val cabinsRows = JSON.parseArray(session("cabinsRows").as[String])
        val customers = JSON.parseArray(session("customers").as[String])
        var sessionParam = JSON.parseObject("{}")
        sessionParam.put("seatMapId", session("seatMapId").as[String])
        sessionParam.put("seatMapResponseId", session("seatMapResponseId").as[String])
        var availSeatsParam = getAvailSeatsParam(customers, cabinsRows, sessionParam ,session("chargeType").as[String])
        session.set("availSeatsParam", availSeatsParam).remove("flightProducts")
      })
  }.exitHereIfFailed

  // 请与getSeatMap  组合使用，或者
  val assignSeats = tryMax(1) {

      exec(http("assignSeats")
        .post("/bookings/${bookingId}/flight/seatAssignments")
        .headers(header_token)
        .body(ElFileBody("execFeeders/PaymentSeatExec/assignSeats.json")).asJson
        .check(status.is(204))
      )
  }.exitHereIfFailed
}
