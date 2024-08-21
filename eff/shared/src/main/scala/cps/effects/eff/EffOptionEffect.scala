package cps.effects.eff

import cats.data.Reader
import cps.effects.OptionEffectCreation
import org.atnos.eff.all.{none as effNone, some as effSome}
import org.atnos.eff.{Eff, |=}

class EffOptionEffect[R, A](using ([X] =>> Option[X]) |= R) extends OptionEffectCreation[[X] =>> Eff[R, X], A] {
  def some(a: A): Eff[R, A] = effSome(a)
  
  def none: Eff[R, A] = effNone
}

given [R, E](using ([X] =>> Reader[E, X]) |= R): EffAskEffect[R, E] = new EffAskEffect
