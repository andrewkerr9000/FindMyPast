package FindMyPast.serialise

import FindMyPast.MySpec

class SerialiseTest extends MySpec {

  "Serialiser" should {
    "return empty string if empty table passed in" in {
      Serialise(Nil, Nil) should be("")
    }

    "serialise a 1 x 1 table" in {
      Serialise(Seq(1), Seq(Seq(1))) should be(
        """#  | 1
           #1 | 1""".stripMargin('#'))
    }
  }
}
