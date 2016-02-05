package FindMyPast.app

import FindMyPast.Printer

trait Config {
  val printer: Printer
}

class App(implicit val config: Config) {
  def run(tableSize: Int): Unit = ???
}
