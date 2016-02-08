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

    "return distinct numbers" in {
      val n = 1000
      val primes = FirstNPrimesGenerator(n)

      for (i <- Seq.range(1, n)) {
        primes.slice(0, i - 1) should not(contain(primes(i)))
      }
    }

    "return monotonically increasing numbers" in {
      val n = 1000
      val primes = FirstNPrimesGenerator(n)

      for (i <- Seq.range(1, n)) {
        primes(i - 1) should be <= primes(i)
      }
    }

    "return only prime numbers" in {
      val n = 1000
      val primes = FirstNPrimesGenerator(n)

      for (primeIndex <- Seq.range(1, n)) {
        // ignore the first prime: we know it's 2
        for (smallerNumber <- Seq.range(2, primeIndex / 2 + 1)) {
          assert(!(primes(primeIndex).toDouble / smallerNumber.toDouble).isWhole(), s"${primes(primeIndex)} is divisible by $smallerNumber")
        }
      }
    }

    "for any consecutive pair of returned primes all integers between them, if any, should be non prime" ignore {
      fail("if this were production code I would write this test")
    }
  }

}
