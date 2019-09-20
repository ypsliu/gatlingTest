package gatling.protocal

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Protocols {

  val testServer = http
    .baseUrl("http://10.200.22.241:6080/CN/tRetailAPI")
    .acceptLanguageHeader("zh-CN")
    //.acceptLanguageHeader("en-US")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
  //.enableHttp2

  val gitTestServer = http
    .baseUrl("https://github.com")

}
