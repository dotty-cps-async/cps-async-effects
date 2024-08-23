package cps.effects.turbolift

import cps.effects.{AskEffectCreation, EffectSystem}
import turbolift.{Computation, Effect}
import turbolift.effects.{Reader, ReaderEffect, ReaderSignature}
import turbolift.internals.effect.CanPerform

class TurboliftAskEffectCreation[F[_], A](using val r: Reader[A], val s: EffectSystem[F]) {
  def ask: Computation[A, r.type] = r.ask
}


class TurbolliftAskEffectCreator2[U, A](using val r: Reader[A], val s: TurboliftEffectSystem[U]) extends AskEffectCreation[[X] =>> Computation[U,X], A] {
  override type FA = Computation[A, r.type]
  def ask: Computation[A, r.type] = r.ask
}

given tlAskEffect[U,A](using r: Reader[A], s: TurboliftEffectSystem[U]): TurboliftAskEffectCreation[[X]=>>Computation[X,U], A] =
  new TurboliftAskEffectCreation[[X]=>>Computation[X,U], A]

type AskEffect[A] = [F[_]] =>> TurboliftAskEffectCreation[F, A] 

def ask[F[_], A](using TurboliftAskEffectCreation[F, A]) = 
  summon[TurboliftAskEffectCreation[F, A]].ask