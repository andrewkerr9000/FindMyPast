package FindMyPast.serialise

import FindMyPast.models.Table

import scala.collection.mutable.ListBuffer

object Serialise {

  def apply(table: Table): String = {
    if (table.header == Nil) return ""

    val maxDigits = {
      // a for comprehension is syntactic sugar over flatMap, filter and one final map
      val digitsOfNumbers = for {
        row <- table.values
        number <- row
      } yield number.toString.length

      digitsOfNumbers.max
    }

    def whiteSpaceOfLength(n: Int) = {
      Seq.fill(n)(" ").mkString
    }

    def serialiseNumber(n: Long): String = {
      val str = n.toString
      whiteSpaceOfLength(maxDigits - str.length + 1) + str + " "
    }

    val buffer = new ListBuffer[String]()
    val separator = "|"

    buffer.append(
      separator +
        whiteSpaceOfLength(maxDigits + 2) +
        separator +
        table.header.map(serialiseNumber).mkString(separator) +
        separator
    )

    buffer.appendAll(
      (table.header zip table.values)
        .map { case (rowHeader, row) => // shorthand for a pattern matching function
          (rowHeader +: row) // a +: b prepends b with a - it's actually equivalent to b.+:(a)
            .map(serialiseNumber)
            .mkString(separator, separator, separator)
        }
    )

    buffer.mkString("\n")
  }
}
