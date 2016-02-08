package FindMyPast.prime

import FindMyPast.MySpec
import org.scalacheck.Gen

class FirstNPrimesGeneratorTest extends MySpec {

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

    // It is implausible that any of the failing tests would pass when getting the first 1000 primes
    // but fail with 999

    val numberOfPrimes = 1000

    "return distinct numbers" in {
      val primes = FirstNPrimesGenerator(numberOfPrimes)

      for (i <- Seq.range(1, numberOfPrimes)) {
        primes.slice(0, i - 1) should not(contain(primes(i)))
      }
    }

    "return monotonically increasing numbers" in {
      val primes = FirstNPrimesGenerator(numberOfPrimes)

      for (i <- Seq.range(1, numberOfPrimes)) {
        primes(i - 1) should be <= primes(i)
      }
    }

    def assertPrime(number: Long) = {
      Seq.range(2, number / 2 + 1).foreach{ divisor =>
        assert(!(number.toDouble / divisor.toDouble).isWhole(), s"number $number is not prime, divisible by $divisor")
      }
    }

    "return only prime numbers" in {
      val primes = FirstNPrimesGenerator(numberOfPrimes)

      primes.foreach(assertPrime)
    }
  }

}
