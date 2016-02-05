package FindMyPast.app

import FindMyPast.Config
import FindMyPast.prime.FirstNPrimesGenerator
import FindMyPast.serialise.Serialise
import FindMyPast.tabulate.Tabulate


// Unlike all the other services which are Objects this needs to be a Class so it can be initialised with a parameter
class MainApp(implicit val config: Config) {
  def run(tableSize: Int): Unit = {
    val primes = FirstNPrimesGenerator(tableSize)
    val table = Tabulate(primes)
    val output = Serialise(table)
    config.printer.print(output)
  }
}
