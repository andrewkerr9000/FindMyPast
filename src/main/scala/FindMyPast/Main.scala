package FindMyPast

import FindMyPast.app.MainApp

import scala.util.{Failure, Success, Try}

object Main extends App {

  implicit object ProdConfig extends Config {
    override val printer: Printer = ConsolePrinter
  }

  val tableSize = Try {
    val arg = args(0).toInt
    require(arg > 0)
    arg
  }

  tableSize match {
    case Success(n) => new MainApp().run(n)
    case Failure(_) => println("Run with a single argument that is a valid Int")
  }
}
