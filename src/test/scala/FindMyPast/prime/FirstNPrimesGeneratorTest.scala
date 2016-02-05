package FindMyPast.prime

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

class FirstNPrimesGeneratorTest extends WordSpec with Matchers with GeneratorDrivenPropertyChecks {

  "First N Primes Generator" should {
    "return Nil if asked for 0 primes" in {
      FirstNPrimesGenerator(0) should be(Nil)
    }

    "return 2 as the first prime" in {
      FirstNPrimesGenerator(1) should be(Seq(2))
    }

    "throw if ask for a negative number of primes" in {
      val nonPositiveInts = Gen.choose(Int.MinValue, 0)

      forAll(nonPositiveInts) { n: Int =>
        whenever(n < 0) {
          intercept[IllegalArgumentException](FirstNPrimesGenerator(n))
        }
      }
    }

    "return requested number of numbers" in {
      val intsThatArentSoBigThatTheTestIsReallySlow = Gen.choose(1, 500)
      forAll(intsThatArentSoBigThatTheTestIsReallySlow) { n: Int =>
        whenever(n >= 0) {
          FirstNPrimesGenerator(n).length should be(n)
        }
      }
    }
  }

}
