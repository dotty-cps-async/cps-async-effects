package cps.effects.catsEffect

import cps.effects.*
import cats.*

class CatsEitherEffect[F[_],E,A](using MonadError[F,E]) extends EitherEffectCreation[F, E, A] {
  def left(e: E): F[A] = summon[MonadError[F,E]].raiseError[A](e)

  def right(a: A): F[A] = summon[Monad[F]].pure(a)
}

given [F[_], E, A](using MonadError[F,E]): CatsEitherEffect[F,E,A] =
  new CatsEitherEffect[F,E,A]


