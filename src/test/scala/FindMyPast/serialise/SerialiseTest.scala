package FindMyPast.serialise

import FindMyPast.MySpec
import FindMyPast.models.Table

class SerialiseTest extends MySpec {

  "Serialiser" should {
    "return empty string if empty table passed in" in {
      Serialise(Table(Nil, Nil)) should be("")
    }

    "serialise a 1 x 1 table" in {
      Serialise(Table(Seq(1), Seq(Seq(1)))) should be(
        """#  | 1
           #1 | 1""".stripMargin('#'))
    }

    "serialise a table with varying numbers of digits" in {
      Serialise(Table(Seq(10, 100), Seq(Seq(100, 1000), Seq(1000, 10000)))) should be(
        """#      |    10 |   100
           #   10 |   100 |  1000
           #  100 |  1000 | 10000""".stripMargin('#'))
    }
  }
}
