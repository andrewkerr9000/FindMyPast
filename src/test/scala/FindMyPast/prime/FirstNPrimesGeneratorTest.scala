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
      val nonPositiveInts = Gen.choose(Int.MinValue, -1)
      forAll(nonPositiveInts) { n: Int =>
        intercept[IllegalArgumentException](FirstNPrimesGenerator(n))
      }
    }

    val intsThatArentSoBigThatTheTestIsReallySlow = Gen.choose(0, 500)
    "return requested number of numbers" in {
      forAll(intsThatArentSoBigThatTheTestIsReallySlow) { n: Int =>
        FirstNPrimesGenerator(n).length should be(n)
      }
    }

    "return distinct numbers" in {
      forAll(intsThatArentSoBigThatTheTestIsReallySlow) { n: Int =>
        val primes = FirstNPrimesGenerator(n)

        for(i <- Seq.range(1, n - 1)) {
          primes.slice(0, i - 1) should not(contain(primes(i)))
        }
      }
    }
  }

}
