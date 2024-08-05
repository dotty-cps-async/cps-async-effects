package cps.effects.catsmt

class MinExample {

  /*
  def testInputAndError() = effectfully[Eff] {
    val sInput = reflect(summon[InputEffect].readLine)
    val x = try {
      sInput.parseInt
    } catch {
      case ex: NumberFormatException =>
        reflect(summon[ErrorEffect].error(s"bad number format: ${ex.getMessage}"))
    }
    reflect(summon[OutputEffect].println(s"parsed: $x"))
  }
  
  def testInputAndErrorEff() = reify[[X]=>>Eff[fx3[Input,Output,Error],X]] {
    val sInput = reflect(summon[InputEffect].readLine)
    val x = try {
      sInput.parseInt
    } catch {
      case ex: NumberFormatException =>
        reflect(summon[ErrorEffect].error(s"bad number format: ${ex.getMessage}"))
    }
    reflect(summon[OutputEffect].println(s"parsed: $x"))
  }
  
  def testInputAndErrorTaglessFinal[F[_]:EffectSystem:Input:Output:Error]() = reify[F] {
    val sInput = reflect(summon[InputEffect].readLine)
    val x = try {
      sInput.parseInt
    } catch {
      case ex: NumberFormatException =>
        reflect(summon[ErrorEffect].error(s"bad number format: ${ex.getMessage}"))
    }
    reflect(summon[OutputEffect].println(s"parsed: $x"))
  }
  */
  
}
