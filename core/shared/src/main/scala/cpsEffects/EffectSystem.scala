package cpsEffects

trait EffectSystem {

   type EffectStack[EL <: EffectList, X ]

   type EffectList

   type Effect

   trait EffectContext[Effect] {
      //  some operations, which we can't write here. 
   }
   
   trait TaglessRepresentation[Effect,F[_]] //  typeclass over f

   //  ???
   trait Combine[ElIn<:EffectList, E<:Effect, ElOut <: EffectList] {

      def combine[X](in:EffectStack[ElIn,X], e: Effect): EffecStack[ElOut,X]
      
   }

}

object EffEffectSystem extends EffectSystem {

   type EffectStack[El <: EffectList] =
       El match
         case X1*:X2:*X3:*X4:*Nil => fx4[X1,X2,X3,X4]
         case X1*:X2:*X3:*Nil => fx3[X1,X2,X3,X4]

   type Effect = 


}

//tagless stype
//
def doSomething[F[_]:TR[Effec1]:TR[Effect2]] :  F[Int]
//
//context style
//
def doSomething(use Effect1,  Effect2): EffectStack[Int]

