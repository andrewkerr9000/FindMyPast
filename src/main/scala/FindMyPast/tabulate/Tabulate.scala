package FindMyPast.tabulate

import FindMyPast.models.Table

object Tabulate {

  def apply(numbers: Seq[Long]): Table = {
    val size = numbers.length
    val values = Seq.tabulate(size, size) { (i, j) =>
      numbers(i) * numbers(j)
    }
    Table(numbers, values)
  }
}
