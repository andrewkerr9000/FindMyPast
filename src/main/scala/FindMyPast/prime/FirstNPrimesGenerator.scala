package FindMyPast.prime

// For an Object Scala will generate a class, and then a single instance within the current scope
// This is not within another class so will be singleton
object FirstNPrimesGenerator {

  // The use of an apply method allows me to use the Object with function-like syntax
  // i.e. FirstNPrimes(3) as well as of FirstNPrimes.apply(3)
  // ??? is a macro that compiles but throws a runtime exception, handy for not-yet-implemented code
  def apply(n: Int): Seq[Int] = Nil

}
