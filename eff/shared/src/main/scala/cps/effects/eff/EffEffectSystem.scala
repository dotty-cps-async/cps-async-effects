package cps.effects.eff

import cps.CpsPureMonadInstanceContext
import cps.effects.EffectSystem
import org.atnos.eff.Eff

class EffEffectSystem[R] extends EffectSystem[[X] =>> Eff[R, X]] with CpsPureMonadInstanceContext[[X] =>> Eff[R, X]] {

  def flatMap[A, B](fa: Eff[R, A])(f: A => Eff[R, B]): Eff[R, B] = fa.flatMap(f)

  def map[A, B](fa: Eff[R, A])(f: A => B): Eff[R, B] = fa.map(f)

  def pure[T](t: T): Eff[R, T] = Eff.pure(t)
}

given [R]: EffEffectSystem[R] = new EffEffectSystem