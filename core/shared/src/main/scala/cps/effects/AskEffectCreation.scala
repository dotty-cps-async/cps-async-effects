package cps.effects

trait AskEffectCreation[F[_], A] {
  def ask: F[A]
}

type AskEffect[A] = [F[_]] =>> AskEffectCreation[F, A]

def ask[F[_], A](using AskEffectCreation[F, A]) = summon[AskEffectCreation[F, A]].ask

//def ask1[F[_]](using CpsMonadContext[F]) = new AskInferArgs[F]
//
//class AskInferArgs[F[_]] {
//  def apply[E](using AskEffectCreation[F, E]): F[E] = summon[AskEffectCreation[F, E]].ask
//}