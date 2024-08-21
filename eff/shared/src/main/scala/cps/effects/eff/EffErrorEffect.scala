package cps.effects.eff

import cps.effects.*
import org.atnos.eff.all.{left as effLeft, right as effRight}
import org.atnos.eff.{Eff, |=}
import org.atnos

class EffEitherEffect[R, E, A](using ([X] =>> Either[E, X]) |= R) extends EitherEffectCreation[[X] =>> Eff[R, X], E, A] {
  def left(e: E): Eff[R, A] = effLeft(e)

  def right(a: A): Eff[R, A] = effRight(a)
}

given [R, E, A](using ([X] =>> Either[E, X]) |= R): EffEitherEffect[R, E, A] = new EffEitherEffect
