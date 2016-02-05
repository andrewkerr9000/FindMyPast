package FindMyPast

trait Printer {
  def print(s: String): Unit
}

object ConsolePrinter extends Printer {
  override def print(s: String): Unit = println(s)
}
