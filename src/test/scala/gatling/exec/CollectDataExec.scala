package gatling.exec

import gatling.common.CommonUtils
import io.gatling.core.Predef._

object CollectDataExec{
  var i = 0
  val addItem = (a: Array[String]) => tryMax(1){
      exec({session=>
          if(i == 0) {
            var v = ""
            a.foreach(v+=_+",")
            CommonUtils.maps.put(i.toString,v.substring(0,v.length-1))
          }
        i = i + 1
        var value =""
        for(b <- 0 to (a.length-1)){
          value =  value+session(a(b)).as[String]+","
        }
        CommonUtils.maps.put(i.toString, value.substring(0,value.length-1))
          session
        })
  }.exitHereIfFailed
}
