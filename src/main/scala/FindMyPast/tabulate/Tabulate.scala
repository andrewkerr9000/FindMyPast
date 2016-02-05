package FindMyPast.tabulate

object Tabulate {

  def apply(numbers: Seq[Int]): Seq[Seq[Int]] = {
    val size = numbers.length
    Seq.tabulate(size, size) { (i, j) =>
      numbers(i) * numbers(j)
    }
  }
}
