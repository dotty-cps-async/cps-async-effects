package cps.effects

trait EffectSystem[M[_]]



trait EffectContext[E[_]] {

}

trait TaglessRepresentation[E[_],F[_]:EffectSystem] {

  def extract[X](e:F[X]): E[X]
  def embed[X](e:E[X]): F[X]

}

object EffectSystem {

  trait InjectTarget[G[_]] {

      def create[F[_]:EffectSystem,E[_]:EffectContext]:EffectSystem[G]

  }

}

