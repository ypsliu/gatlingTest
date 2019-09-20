package gatling.common

import io.gatling.core.Predef._
import net.sf.saxon.functions.ConstantFunction.True

import scala.collection.immutable.ListMap

object DebugExec {

  val IS_DEBUG_ENABLED = true

  val debug = (session: Session) => {
    if (IS_DEBUG_ENABLED) {
      val sortedSessionAttributes = ListMap(session.attributes.toSeq.sortBy(_._1): _*)
      sortedSessionAttributes.foreach { e: (String, Any) =>
        print(e._1)
        print(":\n\t")
        println(e._2)
      }
    }
    session
  }
}