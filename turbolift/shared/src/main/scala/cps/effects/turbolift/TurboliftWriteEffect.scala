package cps.effects.turbolift

import cps.effects.WriteEffectCreation
import turbolift.effects.{WriterEffect, WriterSignature}
import turbolift.{Computation, Effect}
import turbolift.internals.effect.CanPerform

class TurboliftWriteEffect[R, A] extends WriteEffectCreation[[X] =>> Computation[X, R], A] with CanPerform[WriterSignature[A, A]] {
  override type ThisEffect = R

  def tell(a: A): Computation[Unit, ThisEffect] = perform(_.tell(a))
}

given [R, A]: TurboliftWriteEffect[R, A] = new TurboliftWriteEffect[R, A]
