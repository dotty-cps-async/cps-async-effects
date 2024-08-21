package cps.effects.eff

import cats.data.Writer
import cps.effects.WriteEffectCreation
import org.atnos.eff.{Eff, |=}
import org.atnos.eff.all.tell as effTell

class EffWriteEffect[R, E](using ([X] =>> Writer[E, X]) |= R) extends WriteEffectCreation[[X] =>> Eff[R, X], E] {
  def tell(e: E): Eff[R, Unit] = effTell(e)
}

given [R, E](using ([X] =>> Writer[E, X]) |= R): EffWriteEffect[R, E] = new EffWriteEffect
