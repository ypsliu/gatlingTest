package gatling.exec

import com.alibaba.fastjson.{JSON, JSONObject}
import gatling.java.common.{RequestBase, Tools}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.control.Breaks._


object PackageExec{

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

  val createPackageSearch =  tryMax(1) {
    exec({ session =>
      val localStartDate = Tools.getLocalDate(2)
      val localEndDate =  Tools.getLocalDate(5)
      session.set("packageSearchType","ONEWAY")
        .set("origin","CITY_BJS_CN")
        .set("destination","CITY_HAK_CN")
        .set("localStartDate",localStartDate)
        .set("localEndDate",localEndDate)
    })
      .exec(http("createPackageSearch")
        .post("/package/resultSets")
        .headers(header_token)
        .body(ElFileBody("execFeeders/PackageExec/createPackageSearch.json"))
        .check(status.is(201), jsonPath("$.id").saveAs("package_resultSetId"))
        .check(jsonPath("$").saveAs("results"))
      )
  }.exitHereIfFailed

  val getPackageSearchResults = tryMax(1) {

      exec(http("getPackageSearchResults")
        .get("/package/resultSets/${package_resultSetId}")
        .headers(header_token)
        .check(status.is(200))
        .check(jsonPath("$.id").saveAs("resultSetId"))
        .check(jsonPath("$.suppliers").saveAs("suppliers"))
        .check(jsonPath("$.packageOptions").saveAs("packageOptions"))
        .check(jsonPath("$").saveAs("packageSearchResult")))

      .exec({ session =>
        var supplierId: Int = 0
        val suppliers = JSON.parseArray(session("suppliers").as[String])
        val packageOptions = JSON.parseArray(session("packageOptions").as[String])
        var package_OptionsId = ""
        breakable {
          for (index <- 0 until suppliers.size()) {
            if (suppliers.get(index).asInstanceOf[JSONObject].get("code").toString() == "XHOTEL") {
              supplierId = suppliers.get(index).asInstanceOf[JSONObject].get("id").asInstanceOf[Int]
              break
            }
          }
        }
        if(supplierId == 0){
          breakable {
            for (index <- 0 until suppliers.size()) {
              if (suppliers.get(index).asInstanceOf[JSONObject].get("code").toString() == "CTRIP") {
                supplierId = suppliers.get(index).asInstanceOf[JSONObject].get("id").asInstanceOf[Int]
                break
              }
            }
          }
        }

        breakable {
          for (i <- 0 until packageOptions.size()) {
            if (packageOptions.get(i).asInstanceOf[JSONObject].get("hotelOption").asInstanceOf[JSONObject].get("supplierId").asInstanceOf[Int] == supplierId) {
              package_OptionsId = packageOptions.get(i).asInstanceOf[JSONObject].get("id").asInstanceOf[String]
              break
            }
          }
        }
        session.set("package_OptionsId", package_OptionsId).removeAll("suppliers", "packageOptions")
      })
  }.exitHereIfFailed
}
