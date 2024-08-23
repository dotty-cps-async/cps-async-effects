package cps.effects.turbolift

import cps.CpsPureMonadInstanceContext
import cps.effects.EffectSystem
import turbolift.Computation



class TurboliftEffectSystem[U] extends EffectSystem[[X] =>> Computation[X, U]] with CpsPureMonadInstanceContext[[X] =>> Computation[X, U]] {

  def flatMap[A, B](fa: Computation[A, U])(f: A => Computation[B, U]): Computation[B, U] = fa.flatMap(f)

  def map[A, B](fa: Computation[A, U])(f: A => B): Computation[B, U] = fa.map(f)

  def pure[A](a: A): Computation[A, U] = Computation.pure(a)
  
  
}

given [U]: TurboliftEffectSystem[U] = new TurboliftEffectSystem
