package cps.effects.examples

import cps.*
import cps.effects.*


class RandomWinner[F[_]:EffectSystem] {
  
  def winner: F[String] = async[F]{
    val r = scala.util.Random.nextInt(2)
    if (r == 0) "Alice" else "Bob"
  }

  def loser: F[String] = async[F]{
    val r = scala.util.Random.nextInt(2)
    if (r == 0) "Bob" else "Alice"
  }

  def play: F[String] = async[F]{
    val w = await(winner)
    val l = await(loser)
    s"$w wins, $l loses"
  }

}
