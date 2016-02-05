package FindMyPast.serialise

import scala.collection.mutable.ListBuffer

object Serialise {

  def apply(header: Seq[Int], table: Seq[Seq[Int]]): String = {
    require(header.size == table.size)
    if (table == Nil) return ""

    val buffer = new ListBuffer[String]()

    buffer.append("  | " + header.mkString(" | "))

    buffer.appendAll(
      (header zip table).map { case (rowHeader, row) =>
        (rowHeader +: row).mkString(" | ") // a +: b prepends b with a - it's actually a method on b
      }
    )

    buffer.mkString("\n")
  }
}
