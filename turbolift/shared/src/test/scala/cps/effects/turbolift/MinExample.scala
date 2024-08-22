package cps.effects.turbolift

import turbolift.!!
import turbolift.effects.*
import cps.effects.{*, given}
import cps.{*, given}
import cps.monads.{*, given}


object MinExample {

  case object IntReader extends Reader[Int]

  case object StringListReader extends Reader[List[String]]

  case object StringError extends Error[String]

  case object StringWriter extends Writer[String]

  def getRandomItem[F[_] : EffectSystem : EitherEffect[String, String]](number: Int, items: List[String]): F[String] = reify[F] {
    if (items.size >= number) {
      reflect(right(util.Random.shuffle(items).take(number).mkString(", ")))
    } else {
      reflect(left("incorrect"))
    }
  }


  @main def main(): Unit =

    val program1 =
      for
        number <- IntReader.ask
        items <- StringListReader.ask
        randomItems <-
          if items.sizeIs >= number
          then !!.impure(util.Random.shuffle(items).take(number).mkString(", "))
          else StringError.raise("Incorrect number")
        _ <- StringWriter.tell(s"random items: $randomItems")
      yield randomItems

    def program[F[_] : EffectSystem : EitherEffect[String, String] : AskEffect[Int] : AskEffect[List[String]] : WriteEffect[String]]: F[String] = reify[F] {
      val number = reflect(ask[F, Int])
      val items = reflect(ask[F, List[String]])
      val randomItems = reflect(getRandomItem(number, items))
      reflect(tell(s"random items: $randomItems"))
      randomItems
    }

    val result =
      program
        .handleWith(IntReader.handler(3))
        .handleWith(StringListReader.handler(List("1", "2", "3", "4")))
        .handleWith(StringError.handler)
        .handleWith(StringWriter.handler)
        .run

    println(result)

}
