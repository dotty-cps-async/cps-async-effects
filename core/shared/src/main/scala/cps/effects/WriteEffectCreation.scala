package cps.effects

trait WriteEffectCreation[F[_], E] {
  def tell(e: E): F[Unit]
}

type WriteEffect[E] = [F[_]] =>> WriteEffectCreation[F, E]

def tell[F[_], E](e: E)(using WriteEffectCreation[F, E]) = summon[WriteEffectCreation[F, E]].tell(e)

