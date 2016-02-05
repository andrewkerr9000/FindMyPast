package FindMyPast.app

import FindMyPast.{Printer, MySpec}
import org.mockito.Mockito
import org.scalatest.mock.MockitoSugar

class AppTest extends MySpec with MockitoSugar {

  implicit object TestConfig extends Config {
    override val printer: Printer = mock[Printer]("mockPrinter")
  }

  val app = new App

  "App" should {
    "output a table of primes of the required size" in {
      app.run(3)

      Mockito.verify(TestConfig.printer).print(
        """|    |  2 |  3 |  5 |
          #|  2 |  4 |  6 | 10 |
          #|  3 |  6 |  9 | 15 |
          #|  5 | 10 | 15 | 25 |""".stripMargin('#')
      )
    }
  }
}
