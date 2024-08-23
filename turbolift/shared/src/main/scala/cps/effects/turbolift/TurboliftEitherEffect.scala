package cps.effects.turbolift

import cps.effects.{EffectSystem, EitherEffectCreation}
import turbolift.{Computation, Effect}
import turbolift.effects.{Error, ErrorEffect, ErrorSignature}
import turbolift.internals.effect.CanPerform

class TurboliftEitherEffectCreator[F[_], E, A](using val r: Error[E], val s: EffectSystem[F])  {
  type ThisEffect = r.type
  
  def left(e: E): Computation[A, r.type ] = r.raise(e)

  def right(a: A): Computation[A, Any ] = Computation.pure(a)
}

given turbolifEither[E, A, U](using Error[E], TurboliftEffectSystem[U]): TurboliftEitherEffectCreator[[X]=>>Computation[X,U], E, A] =
  new TurboliftEitherEffectCreator[[X]=>>Computation[X,U],E, A]

type EitherEffect[E, A] = [F[_]] =>> TurboliftEitherEffectCreator[F, E, A]
