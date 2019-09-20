package gatling.protocal

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Protocols {

  val default = http
    .baseUrl("http://localhost:8080")
        .acceptLanguageHeader("zh-CN")
    //.acceptLanguageHeader("en-US")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val teamEnvironment = http
    .baseUrl("http://team-star-slave3:6080/CN/tRetailAPI")
    //    .acceptLanguageHeader("zh-CN")
    .acceptLanguageHeader("zh-CN")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val testServer = http
    .baseUrl("http://192.168.23.95:6080/CN/tRetailAPI")
    .acceptLanguageHeader("zh-CN")
    //.acceptLanguageHeader("en-US")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    //.enableHttp2

  val gitTestServer = http
    .baseUrl("https://github.com")

  val testLocation = http
    .baseUrl("http://team-smedalian.openjawtech.com/CN/LocationAPI/")
    .acceptLanguageHeader("zh-CN")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
}
