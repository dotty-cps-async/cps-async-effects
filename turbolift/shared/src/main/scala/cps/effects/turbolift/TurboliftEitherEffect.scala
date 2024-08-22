package cps.effects.turbolift

import cps.effects.EitherEffectCreation
import turbolift.{Computation, Effect}
import turbolift.effects.{ErrorEffect, Error, ErrorSignature}
import turbolift.internals.effect.CanPerform

class TurboliftEitherEffect[E, A] extends EitherEffectCreation[[X] =>> Computation[X, Error[E, E]], E, A] with CanPerform[ErrorSignature[E, E]]{
  override type ThisEffect = Error[E]
  
  override def left(e: E): Computation[A, ErrorEffect[E, E]] = perform(_.raise(e))

  override def right(a: A): Computation[A, ErrorEffect[E, E]] = Computation.pure(a)
}

given [E, A]: TurboliftEitherEffect[E, A] = new TurboliftEitherEffect[E, A]


