package gatling.common

import java.util
import java.util.concurrent.ConcurrentHashMap

object CommonUtils {

  val maps:ConcurrentHashMap[String,String] = new ConcurrentHashMap[String,String]()
//  val lists:util.ArrayList[String] = new util.ArrayList[String]()

  def getRadomInt(): Integer = {
    var times :Integer = (new util.Random).nextInt(15)
    if(times <=0) times = 1
    return times
  }

  def main(args: Array[String]): Unit = {
    println(getRadomInt)
  }

}
