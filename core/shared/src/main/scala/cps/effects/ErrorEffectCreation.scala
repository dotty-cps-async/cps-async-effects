package cps.effects

trait ErrorEffectCreation[F[_], E] {
  def raise(t: E): F[Nothing]
}

type ErrorEffect[E] = [F[_]] =>> ErrorEffectCreation[F,E]