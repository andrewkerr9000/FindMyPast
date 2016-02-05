package FindMyPast.app

import FindMyPast.Printer
import FindMyPast.prime.FirstNPrimesGenerator
import FindMyPast.serialise.Serialise
import FindMyPast.tabulate.Tabulate

trait Config {
  val printer: Printer
}

class App(implicit val config: Config) {
  def run(tableSize: Int): Unit = {
    val primes = FirstNPrimesGenerator(tableSize)
    val table = Tabulate(primes)
    val output = Serialise(table)
    config.printer.print(output)
  }
}
