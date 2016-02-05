package FindMyPast.prime

import org.scalatest.{Matchers, WordSpec}

class FirstNPrimesGeneratorTest extends WordSpec with Matchers {

  "First N Primes Generator" should {
    "return Nil if asked for 0 primes" in {
      FirstNPrimesGenerator(0) should be(Nil)
    }

    "throw if ask for a negative number of primes" in {
      intercept[IllegalArgumentException](FirstNPrimesGenerator(-1))
    }
  }

}
