package cps.effects.eff

import cats.Eval
import cps.effects.EvalEffectCreation
import org.atnos.eff.all.delay
import org.atnos.eff.{Eff, |=}

class EffEvalEffect[R, E](using ([X] =>> Eval[X]) |= R) extends EvalEffectCreation[[X] =>> Eff[R, X], E] {
  def eval(e: => E): Eff[R, E] = delay(e)
}

given [R, E](using ([X] =>> Eval[X]) |= R): EffEvalEffect[R, E] = new EffEvalEffect
