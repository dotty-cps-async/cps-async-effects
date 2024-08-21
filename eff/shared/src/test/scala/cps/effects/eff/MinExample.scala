package cps.effects.eff

import cats.*
import cats.data.*
import org.atnos.eff.{EitherEffect => _, *, given}
import org.atnos.eff.syntax.all.{*, given}
import cps.effects.{*, given}
import cps.{*, given}
import cps.monads.{*, given}
import org.atnos.eff.{Eff, Fx, |=}

object MinExample {

  type ReaderInt[A] = Reader[Int, A]
  type ReaderStringList[A] = Reader[List[String], A]
  type WriterString[A] = Writer[String, A]
  type EitherString[A] = Either[String, A]

  type Stack = Fx.fx4[WriterString, ReaderInt, ReaderStringList, EitherString]

  def getRandomItem[F[_] : EffectSystem : EitherEffect[String, String]](number: Int, items: List[String]): F[String] = reify[F] {
    if (items.size >= number) {
      reflect(right(util.Random.shuffle(items).take(number).mkString(", ")))
    } else {
      reflect(left("incorrect"))
    }
  }

  def program[F[_] : EffectSystem : EitherEffect[String, String] : AskEffect[Int] : AskEffect[List[String]] : WriteEffect[String]]: F[String] = reify[F] {
    val number = reflect(ask[F, Int])
    val items = reflect(ask[F, List[String]])
    val randomItems = reflect(getRandomItem(number, items))
    reflect(tell(s"random items: $randomItems"))
    randomItems
  }
  
  @main
  def main(): Unit = println {
    program[[X] =>> Eff[Stack, X]]
      .runReader(3)
      .runReader(List("1", "2", "3", "4"))
      .runEither[String]
      .runWriter[String]
      .run
  }
}
