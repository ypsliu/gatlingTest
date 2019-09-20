package gatling.exec

import java.util.UUID
import java.util.concurrent.ThreadLocalRandom

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ProfileExec {

  val createUserProfile =
    exec { session =>
      val randomString = "x".concat(UUID.randomUUID().toString.replaceFirst("^.*-", ""))
      val randomNumber = s"00000000${ThreadLocalRandom.current().nextInt(99999999)}".replaceFirst("^.*(.{8})$", "$1")
      session.setAll(
        "email" ->
          s"EMAIL$randomString@openjawtech.com",
        "mobileNumber" ->
          "133".concat(randomNumber),
        "docId" ->
          s"DOC$randomNumber",
        "userId" ->
          s"USER$randomNumber",
        "password" ->
          "102938"
      )
    }.exec(http("createUserProfile")
      .post("/profile")
      .body(ElFileBody("scenarioBodies/${scenarioBodyPath}/createUserProfile.json"))
      .check(status.is(201))
    )
}
