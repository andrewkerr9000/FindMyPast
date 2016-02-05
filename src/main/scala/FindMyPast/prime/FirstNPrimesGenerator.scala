package FindMyPast.prime

import scala.annotation.tailrec

// For an Object Scala will generate a class, and then a single instance within the current scope
// This is not within another class so will be singleton
object FirstNPrimesGenerator {

  // The use of an apply method allows me to use the Object with function-like syntax
  // i.e. FirstNPrimes(3) as well as of FirstNPrimes.apply(3)

  // Sieve of Eratosthenes modified to return first N primes rather than primes <= N
  // Requires infinite lazy lists - Stream in Scala
  def apply(n: Int): Seq[Long] = {
    require(n >= 0)
    // Sometimes an early return can make the rest of a method simpler. I don't like returning in the middle of a method
    if(n == 0) return Nil

    def knownMultiplesOf(n: Long) = Stream.iterate(start = n)(_ + n)

    // storing the length so it doesn't need to be recalculated every time - which is O(n) on most Scala collections
    @tailrec // not required for tail call optimisation, but makes the compiler moan if it is not possible
    def recur(candidate: Long, length: Int, knownPrimes: Seq[Long], knownNonPrimes: Seq[Stream[Long]]): Seq[Long] = {
      @tailrec
      def notContained(candidate: Long, stream: Stream[Long]): Boolean = {
        if (candidate == stream.head) {
          false
        } else if (stream.head > candidate) {
          true
        } else {
          notContained(candidate, stream.tail)
        }
      }

      if(length == n) {
        knownPrimes
      } else {
        if(knownNonPrimes.forall(notContained(candidate, _))) {
          recur(candidate + 1, length + 1, knownPrimes :+ candidate, knownNonPrimes :+ knownMultiplesOf(candidate))
        } else {
          recur(candidate + 1, length, knownPrimes, knownNonPrimes)
        }
      }
    }

    val firstPrime = 2
    recur(candidate = firstPrime + 1, length = 1, knownPrimes = Seq(firstPrime), knownNonPrimes = Seq(knownMultiplesOf(firstPrime)))
  }

}
