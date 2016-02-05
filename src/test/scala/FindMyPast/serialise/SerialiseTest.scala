package FindMyPast.serialise

import FindMyPast.MySpec

class SerialiseTest extends MySpec {

  "Serialiser" should {
    "return empty string if empty Seq passed in" in {
      Serialise(Nil) should be("")
    }
  }
}
