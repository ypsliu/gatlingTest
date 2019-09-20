package gatling.exec

import gatling.java.common.RequestBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.collection.mutable

object EzpayExec{

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

  val getPaymentOptions =
    exec(http("GET /ezpay/paymentOptions/{bookingId}")
      .get("/ezpay/paymentOptions/${bookingId}")
      .headers(header_token)
      .check(status.is(200))
      .check(jsonPath("$..*[?(@.bankName == 'Test Bank' || @.bankName == 'Test Bank ')]").ofType[Map[String, Any]].saveAs("bank")))

  val authoriseRedirect = tryMax(1) {

      exec(http("authoriseRedirect")
        .post("/ezpay/authoriseRedirect")
        .headers(header_token)
        .body(ElFileBody("execFeeders/EzpayExec/authoriseRedirect.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.url").saveAs("ezpayUrl_t")))
      .exec({ session =>
        val ezpayUrl = session("ezpayUrl_t").as[String].replaceAll("[|]", "%7C")
        session.remove("ezpayUrl_t").set("ezpayUrl", ezpayUrl)
      })
  }.exitHereIfFailed


  val redirectUrl = tryMax(1){

    exec(http("redirectUrl")
        .get("${ezpayUrl}")
        .headers(header_refer)
        .check(status.is(200), bodyString.saveAs("ezpayAirlinepayServletResponse")))
    .exec({ session =>
      val form = session("ezpayAirlinepayServletResponse").as[String].replaceAll("(?si).*(<form.*?</form>).*", "$1")
      val url = form.replaceAll("(?si).*action=\"(.+?)\".*", "$1")
      val signature = form.replaceAll("(?si).*signature.*?value=\"(.+?)\".*", "$1")
      val sysbillno = form.replaceAll("(?si).*sysbillno.*?value=\"(.+?)\".*", "$1")
      val appname = form.replaceAll("(?si).*appname.*?value=\"(.+?)\".*", "$1")
      val paramMap = Map[String, String]("signature" -> signature, "sysbillno" -> sysbillno, "appname" -> appname)
      session.remove("ezpayAirlinepayServletResponse").set("easyPayResPageURL", url).set("easyPayResPageParams", paramMap)
    })}.exitHereIfFailed

  val get_detail = tryMax(1){

    exec(http("get_detail")
        .get("${easyPayResPageURL}")
        .queryParamMap("${easyPayResPageParams}")
        .check(status.is(200), bodyString.saveAs("easyPayResPageResponse")))
    .exec({ session =>
      val form = session("easyPayResPageResponse").as[String].replaceAll("(?si).*(<form.*?</form>).*", "$1")
      val url = form.replaceAll("(?si).*action=\"(.+?)\".*", "$1")
      var paramMap = new mutable.HashMap[String,String]()
      val inputNames = List("payamount", "apptype", "bankid", "billno", "ext1", "ext2", "lan", "msg", "ordercurtype", "ordertype",
        "orgid", "paydate", "paytime", "orderno", "paystatus", "returntype", "signature")
      for (paramName: String <- inputNames) {
        val paramValue = form.replaceAll(s"""(?si).*$paramName.*?value="(.*?)".*""", "$1").replaceAll("\\r?\\n", "").replaceAll("=", "%3D").replaceAll("[+]", "%2B").replaceAll("[|]", "%7C")
        paramMap += (paramName -> (if (paramName.startsWith("ext")) "测试" else paramValue))
      }
      session.remove("easyPayResPageResponse").set("redirectPaymentCallbackURL", url)//.set("redirectPaymentCallbackParams", paramMap)
      session.setAll(paramMap)//.set("ezpayDetailMap",paramMap)
    })}.exitHereIfFailed

  val confirmRedirect = tryMax(1){

    exec(http("confirmRedirect")
      .post("/ezpay/confirmRedirect")
      .headers(header_token)
      .body(ElFileBody("execFeeders/EzpayExec/confirmRedirect.json")).asJson
      .check(status.is(204)))}.exitHereIfFailed
}
