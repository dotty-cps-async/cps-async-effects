package cps.effects

import cps.{CpsMonad, CpsTryMonad}

import scala.annotation.compileTimeOnly
import scala.compiletime.*
import scala.quoted.Type

sealed trait EffectList

object EffectList {

  case class Cons[H[_],T<:EffectList](head: Effect[H], tail:T) extends EffectList

  case object Empty extends EffectList

}

trait EffectfullBeforeMacro[+T]

//TODO: we don't know how to build EL-Type
trait EffectfullBeforeMacroWithEL[EL <: EffectList,+T]

//transparent inline def effectfully[A](inline block: A): EffectfullBeforeMacro[A] =
//  ???

@compileTimeOnly("effectfullBefore macro should be expanded, check if cps plugin is enabled")
given effectfullBeforeMacroMonad: CpsTryMonad[EffectfullBeforeMacro] = ???

trait Effect[H[_]] {

  type EffectType[X] = H[X]

  type Famity <: EffectSystemFamily

  def family: Famity

}



trait EffectSystem[M[_]] extends CpsMonad[M]

trait EffectSystemWithError[M[_]] extends EffectSystem[M] with CpsTryMonad[M] {

  def errorEffect: Effect[?]

}



trait EffectContext[E[_]]  {

}


trait EffectSystemBuilder[EL <: EffectList](using val effectList: EL) {

  type M[_]

  def effectSystem: EffectSystem[M]


}

trait EffectSystemFamily {

  transparent inline def builder[EL<: EffectList](el:EL): EffectSystemBuilder[EL]

}

object EffectSystem {

  transparent inline def build[EL <: EffectList:Type](efectList: EL): EffectSystem[?] = {
    summonFrom {
      case builder: EffectSystemBuilder[EL] =>
        builder.effectSystem
      case _ =>
        error(s"Can't find builder for ${summon[Type[EL]]}")
    }
  }

}
