package cps.effects.eff

import cats.data.{Reader, Writer}
import cps.effects.AskEffectCreation
import org.atnos.eff.all.ask as effAsk
import org.atnos.eff.{Eff, |=}

class EffAskEffect[R, E](using ([X] =>> Reader[E, X]) |= R) extends AskEffectCreation[[X] =>> Eff[R, X], E] {
  def ask: Eff[R, E] = effAsk[R, E]
}

given [R, E](using ([X] =>> Reader[E, X]) |= R): EffAskEffect[R, E] = new EffAskEffect