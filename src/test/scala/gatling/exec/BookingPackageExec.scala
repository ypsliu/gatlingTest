package gatling.exec

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.alibaba.fastjson._
import gatling.java.common.RequestBase

object BookingPackageExec{

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

  val addPackage = tryMax(1){

      exec(http("addPackage")
        .post("/bookings/${bookingId}/package")
        .headers(header_token)
        .body(ElFileBody("execFeeders/BookingPackageExec/addPackage.json"))
        .check(status.is(201))
        .check(bodyString.saveAs("response_addPackage"))
      )
      .exec({session =>
        val packageId =JSON.parseObject(session("response_addPackage").as[String]).get("packageId").asInstanceOf[Int]
        session.set("packageId", packageId).remove("response_addPackage")
      })}.exitHereIfFailed
}