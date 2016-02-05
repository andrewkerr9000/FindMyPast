package FindMyPast.tabulate

import org.scalatest.{Matchers, WordSpec}

class TabulateTest extends WordSpec with Matchers {

  "Number Tabulate" should {
    "return empty if parameter is empty" in {
      Tabulate(Nil) should be(Nil)
    }
  }
}
