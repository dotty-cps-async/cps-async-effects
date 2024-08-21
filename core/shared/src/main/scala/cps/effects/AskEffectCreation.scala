package cps.effects

trait AskEffectCreation[F[_], E] {
  def ask: F[E]
}

type AskEffect[E] = [F[_]] =>> AskEffectCreation[F,E]

def ask[F[_], E](using AskEffectCreation[F, E]) = summon[AskEffectCreation[F, E]].ask 

//def ask1[F[_]](using CpsMonadContext[F]) = new AskInferArgs[F]
//
//class AskInferArgs[F[_]] {
//  def apply[E](using AskEffectCreation[F, E]): F[E] = summon[AskEffectCreation[F, E]].ask
//}