package gatling.common

import gatling.java.common.RequestBase

trait ExecBase{
  var rq: RequestBase = null
  var body: String = null
  var method: String = null
  var url: String = null
  var headers = Map.empty[String, String]
  var result: String = "0"
  var paras = Map.empty[String, Any]
  var response: String = null
  var st: Int = 200

  val header_token= Map(
    "User-Token" -> "${User-Token}"
  )
  val header_refer= Map(
    "Referer" -> "http://119.254.234.92:9280/easypay/"
  )
  val test_json= Map(
    "bookingId" -> "${bookingId}"
  )

//  val sendPost = (url:String, headers:Map[String,String], body:String, st:Int)=> {
//    exec(http("121212")
//      .post(url)
//      .headers(headers)
//      .body(StringBody(body)).asJson
//      .check(status.is(st))
//      .check(bodyString.saveAs("response")))
//  }
//
//  val sendPost_2 =
//    exec(http("12111111212")
//      .post(url)
//      .headers(headers)
//      .body(StringBody(body)).asJson
//      .check(status.is(st))
//      .check(bodyString.saveAs("response")))
//
//  val sendGet = (url:String, headers:Map[String,String], body:String, st:Int)=>
//    exec(http("121212")
//      .post(url)
//      .headers(headers)
//      .body(StringBody(body)).asJson
//      .check(status.is(st))
//      .check(bodyString.saveAs("response"),jsonPath("$.token").saveAs("User-Token")))
}