package gatling.exec

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import gatling.java.common.RequestBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.apache.commons.lang.StringUtils

object LocationExec{

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

  val getLocations = tryMax(1) {

      exec(http("getLocations")
        .get("/locations?searchString=${searchString}&locationType=${locationType}&country=${country}&productTypes=${productTypes}")
        .check(status.is(200))
        .check(jsonPath("$").saveAs("getLocationsRs"))
      )
  }.exitHereIfFailed

  val getParentLocations = tryMax(1) {

      exec(http("getParentLocations")
        .get("/locations/${locationCode}/parents")
        .check(status.is(200))
      )
  }.exitHereIfFailed

  val getChildrenLocations = tryMax(1) {

      exec(http("getChildrenLocations")
        .get("/locations/${locationCode}/children?limit=${limit}&locationType=${locationType}&locationCategory=${locationCategory}")
        .check(status.is(200))
      )
  }.exitHereIfFailed

  val getLocationByCode = tryMax(1) {

      exec(http("getLocationByCode")
        .get("/locations/${locationCode}")
        .check(status.is(200)) //,bodyString.saveAs("resjson")
      )
  }.exitHereIfFailed

  val getClosestCityLocation = tryMax(1) {

      exec(http("getClosestCityLocation")
        .get("/locations/closestCityLocation?productTypes=${productTypes}&longitude=${longitude}&latitude=${latitude}")
        .check(status.is(200)) //,bodyString.saveAs("resjson")
      ).exec({ session =>
      session.set("url", url).set("headers", headers).set("body", body)
    })
  }.exitHereIfFailed

  val getAirportLocations = tryMax(1) {

      exec(http("getAirportLocations")
        .get("/locations/airports")
        .check(status.is(200)) //,bodyString.saveAs("resjson")
      )
  }.exitHereIfFailed

  val parent_location_url = "/locations/{locationCode}/parents"
  val children_location_url = "/locations/{locationCode}/children"
  val closest_city_location_url = "/locations/closestCityLocation"
  val code_location_url = "/locations/{locationCode}"
  val airports_location_url = "/locations/airports"
  val get_locations_url = "/locations"

  def return_url_params(params: JSONArray): String = {
    var result: String = "";
    for (index <- 0 until params.size()) {
      var param = params.get(index).asInstanceOf[JSONObject]
      var it = param.entrySet().iterator()
      var paramName = "";
      var paramValue = "";
      while (it.hasNext) {
        paramName = it.next().getKey
      }
      if (StringUtils.isNotBlank(param.getString(paramName))) {
        result += "&" + paramName + "=" + param.getString(paramName)
      }
    }
    return result.replaceFirst("&", "?")
  }
}
