package cps.effects

trait EitherEffectCreation[F[_], E, A] {
  def left(l: E): F[A]

  def right(r: A): F[A]
}

type EitherEffect[E, A] = [F[_]] =>> EitherEffectCreation[F, E, A]

def left[F[_], E, A](e: E)(using EitherEffectCreation[F, E, A]) = summon[EitherEffectCreation[F, E, A]].left(e)

def right[F[_], E, A](a: A)(using EitherEffectCreation[F, E, A]) = summon[EitherEffectCreation[F, E, A]].right(a)