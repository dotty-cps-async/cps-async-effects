package cps.effects.turbolift

import cps.effects.AskEffectCreation
import turbolift.{Computation, Effect}
import turbolift.effects.{Reader, ReaderEffect, ReaderSignature}
import turbolift.internals.effect.CanPerform

class TurboliftAskEffect[R <: ReaderAux[A, ThisEffect]: Reader, A, ThisEffect] extends AskEffectCreation[[X] =>> Computation[X, R], A] {

  def ask = summon[Reader[R]].ask
}

given [R <: ReaderAux[A, ThisEffect]: Reader, A, ThisEffect]: TurboliftAskEffect[R, A, ThisEffect] = new TurboliftAskEffect

type ReaderAux[E, X] = ReaderEffect[E] {
 type ThisEffect = X
}