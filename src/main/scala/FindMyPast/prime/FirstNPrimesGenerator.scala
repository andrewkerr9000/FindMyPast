package FindMyPast.prime

// For an Object Scala will generate a class, and then a single instance within the current scope
// This is not within another class so will be singleton
object FirstNPrimesGenerator {

  // The use of an apply method allows me to use the Object with function-like syntax
  // i.e. FirstNPrimes(3) as well as of FirstNPrimes.apply(3)
  def apply(n: Int): Seq[Int] = {
    require(n >= 0)
    Seq.iterate(start = 2, len = n)(_ + 1) // (_ + 1) is shorthand for (a => a + 1)
  }

}
