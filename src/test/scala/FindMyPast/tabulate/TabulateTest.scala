package FindMyPast.tabulate

import FindMyPast.MySpec

class TabulateTest extends MySpec {

  "Number Tabulate" should {
    "return empty if parameter is empty" in {
      Tabulate(Nil).values should be(Nil)
    }

    "return table where position (i,j) equals input(i) * input(j)" in {
      forAll{ input: Seq[Long] =>
        val length = input.length
        val table = Tabulate(input).values
        val indexes = Seq.range(0, length)

        for(i <- indexes; j <- indexes) {
          table(i)(j) should be(input(i) * input(j))
        }
      }
    }
  }
}
