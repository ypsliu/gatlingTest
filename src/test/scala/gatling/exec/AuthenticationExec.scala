package gatling.exec

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AuthenticationExec {//extends ExecBase {
  val header_token= Map(
    "User-Token" -> "${User-Token}"
  )
  val header_refer= Map(
    "Referer" -> "http://119.254.234.92:9280/easypay/"
  )
  val test_json= Map(
    "bookingId" -> "${bookingId}"
  )

  val login = tryMax(1){
    feed(csv("execFeeders/login.csv").batch.circular)
      .exec(
        http("login") //"Thread.currentThread().getStackTrace()(2).getMethodName())
          .post("/authentication")
          //.headers("${headers}".asInstanceOf[mutable.Map[String,String]].toMap)
          .body(ElFileBody("execFeeders/AuthenticationExec/login.json")).asJson
          .check(status.is(201))
          .check(jsonPath("$.token").saveAs("User-Token"))
        //          .check(jsonPath("$.token").saveAs("userIDs"))
      )
    //      .exec({session=>
    //      CommonUtils.maps.put(session("userIDs").as[String],session("userIDs").as[String])
    //        session
    //    })
  }.exitHereIfFailed

  /*val sendMobileAuthentication = tryMax(1) {
    feed(csv("execFeeders/mobileAuthentication.csv").batch.circular)
      .exec(
        http("sendMobileAuthentication")
          .post("/mfa/mobile")
          .body(ElFileBody("execFeeders/AuthenticationExec/sendMobileAuthentication.json")).asJson
          .check(status.is(201)))
  }.exitHereIfFailed
*/
}
