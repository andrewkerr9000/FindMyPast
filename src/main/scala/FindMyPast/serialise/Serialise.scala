package FindMyPast.serialise

import scala.collection.mutable.ListBuffer

object Serialise {

  def apply(header: Seq[Int], table: Seq[Seq[Int]]): String = {
    require(header.size == table.size)
    if (table == Nil) return ""

    val maxDigits = {
      // a for comprehension is syntactic sugar over flatMap, filter and one final map
      val digitsOfNumbers = for {
        row <- table
        number <- row
      } yield number.toString.length

      digitsOfNumbers.max
    }

    def whiteSpaceOfLength(n: Int) = {
      Seq.fill(n)(" ").mkString
    }

    def serialiseNumber(n: Int): String = {
      val str = n.toString
      whiteSpaceOfLength(maxDigits - str.length) + str
    }

    val buffer = new ListBuffer[String]()
    val separator = " | "

    buffer
      .append(
        whiteSpaceOfLength(maxDigits) +
        separator +
        header.map(serialiseNumber).mkString(separator)
      )

    buffer.appendAll(
      (header zip table).map { case (rowHeader, row) =>
        (rowHeader +: row) // a +: b prepends b with a - it's actually a method on b
          .map(serialiseNumber)
          .mkString(separator)
      }
    )

    buffer.mkString("\n")
  }
}
