package cps.effects

trait EvalEffectCreation[F[_], E] {
  def eval(e: => E): F[E]
}

type EvalEffect[E] = [F[_]] =>> EvalEffectCreation[F,E]

def eval[F[_], E](using EvalEffectCreation[F, E]) = summon[EvalEffectCreation[F, E]].eval
