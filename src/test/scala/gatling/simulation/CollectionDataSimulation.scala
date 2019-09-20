package gatling.simulation

import java.io._

import gatling.common.CommonUtils
import gatling.protocal.Protocols
import gatling.scenario.singleAPITest.CreateBookingCollectionData
import io.gatling.core.Predef.{Simulation, nothingFor, _}

import scala.collection.JavaConverters._
import scala.concurrent.duration._

class CollectionDataSimulation  extends Simulation {
  setUp(
    CreateBookingCollectionData.createData("Simple Test")
      .inject(
        nothingFor(2 seconds), // 1
        rampUsers(2000)during(2 minutes), // 2
      ).protocols(Protocols.testServer)
  )

  before {

  }

  after {

    val writer = new PrintWriter(new File("src/test/resources/test.csv"))

    val title = CommonUtils.maps.get("0")
    writer.write("0")
    writer.write(",")
    writer.write(title)
    writer.write("\r\n")
    CommonUtils.maps.remove("0")

    for( key:String <- CommonUtils.maps.keySet().asScala){
      writer.write(key)
      writer.write(",")
      writer.write(CommonUtils.maps.get(key))
      writer.write("\r\n")
    }
    writer.close()
  }
}
