package FindMyPast

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{Matchers, WordSpec}

abstract class MySpec extends WordSpec with Matchers with GeneratorDrivenPropertyChecks
