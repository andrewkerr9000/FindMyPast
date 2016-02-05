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

        for(i <- Seq.range(1, n)) {
          primes.slice(0, i - 1) should not(contain(primes(i)))
        }
      }
    }

    "return monotonically increasing numbers" in {
      forAll(intsThatArentSoBigThatTheTestIsReallySlow) { n: Int =>
        val primes = FirstNPrimesGenerator(n)

        for(i <- Seq.range(1, n)) {
          primes(i - 1) should be <= primes(i)
        }
      }
    }

    "return only prime numbers" in {
      forAll(intsThatArentSoBigThatTheTestIsReallySlow) { n: Int =>
        val primes = FirstNPrimesGenerator(n)

        for(primeIndex <- Seq.range(1, n)) { // ignore the first prime: we know it's 2
          for(smallerNumbers <- Seq.range(2, primeIndex / 2 + 1)) {
            (primes(primeIndex).toDouble / smallerNumbers.toDouble).isWhole() should be(false)
          }
        }
      }
    }
  }

}
