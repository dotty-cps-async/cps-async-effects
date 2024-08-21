package cps.effects

trait OptionEffectCreation[F[_], A] {
  def some(a: A): F[A]

  def none: F[A]
}

type OptionEffect[A] = [F[_]] =>> OptionEffectCreation[F,A]

def some[F[_], A](a: A)(using OptionEffectCreation[F, A]) = summon[OptionEffectCreation[F, A]].some(a)

def none[F[_], A](using OptionEffectCreation[F, A]) = summon[OptionEffectCreation[F, A]].none
