package cps.effects.eff

import cats.data.Reader
import cps.effects.AskEffectCreation
import org.atnos.eff.all.ask as effAsk
import org.atnos.eff.{Eff, |=}

class EffAskEffect[R, A](using ([X] =>> Reader[A, X]) |= R) extends AskEffectCreation[[X] =>> Eff[R, X],A] {
  def ask: Eff[R, A] = effAsk[R, A]
}

given [R, A](using ([X] =>> Reader[A, X]) |= R): EffAskEffect[R, A] = new EffAskEffect