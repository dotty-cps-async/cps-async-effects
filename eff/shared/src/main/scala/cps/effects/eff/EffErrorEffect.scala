package cps.effects.eff

import cps.effects.*
import org.atnos.eff.all.left
import org.atnos.eff.{Eff, |=}

class EffErrorEffect[R, E](using ([X] =>> Either[E, X]) |= R) extends ErrorEffectCreation[[X] =>> Eff[R, X], E] {
  def raise(t: E): Eff[R, Nothing] = left(t)
}

given [R, E](using ([X] =>> Either[E, X]) |= R): EffErrorEffect[R, E] = new EffErrorEffect
