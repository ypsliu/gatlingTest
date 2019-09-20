package gatling.common

import gatling.exec.AuthenticationExec._
import gatling.java.authentication.LoginRq
import gatling.java.common.RequestBase
import io.gatling.core.Predef._
import io.gatling.core.session.Session
import io.gatling.http.Predef._

trait SendRq{
  def sendPost(url:String, headers:Map[String,String], body:String, st:Int)={
    exec(http("121212")
      .post(url)
      .headers(headers)
      .body(StringBody(body)).asJson
      .check(status.is(st))
      .check(bodyString.saveAs("response"),jsonPath("$.token").saveAs("User-Token")))
      //.check(bodyString.saveAs("response")))
  }
  val sendGet = (url:String, headers:Map[String,String], body:String, st:Int)=>
    exec(http("121212")
      .post(url)
      .headers(headers)
      .body(StringBody(body)).asJson
      .check(status.is(st))
      .check(bodyString.saveAs("response"),jsonPath("$.token").saveAs("User-Token")))
}