package FindMyPast.prime

import scala.annotation.tailrec

// For an Object Scala will generate a class, and then a single instance within the current scope
// This is not within another class so will be singleton
object FirstNPrimesGenerator {

  // The use of an apply method allows me to use the Object with function-like syntax
  // i.e. FirstNPrimes(3) as well as of FirstNPrimes.apply(3)

  // Sieve of Eratosthenes modified to return first N primes rather than primes <= N
  def apply(n: Int): Seq[Long] = {
    require(n >= 0)
    // Sometimes an early return can make the rest of a method simpler.
    // I don't like returning in the middle of a method though. It's unclear.
    if(n == 0) return Nil

    // storing the length so it doesn't need to be recalculated every time - which is O(n) on most Scala collections
    @tailrec // not required for tail call optimisation, but makes the compiler moan if it is not possible
    def constructPrimesFrom(candidate: Long, length: Int, knownPrimes: Seq[Long]): Seq[Long] = {
      def notAMultipleOf(candidate: Long, prime: Long): Boolean = {
        !(candidate.toDouble / prime.toDouble).isWhole()
      }

      if(length == n) {
        knownPrimes
      } else {
        // if the next number isn't a multiple of any smaller primes then it itself is prime
        if(knownPrimes.forall(notAMultipleOf(candidate, _))) {
          constructPrimesFrom(candidate + 1, length + 1, knownPrimes :+ candidate)
        } else {
          constructPrimesFrom(candidate + 1, length, knownPrimes)
        }
      }
    }

    val firstPrime = 2
    constructPrimesFrom(candidate = firstPrime + 1, length = 1, knownPrimes = Seq(firstPrime))
  }

}
