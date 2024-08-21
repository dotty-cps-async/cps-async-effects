package cps.effects.turbolift

import turbolift.!!
import turbolift.effects.*


object MinExample {

  case object IntReader extends Reader[Int]

  case object StringListReader extends Reader[List[String]]

  case object StringError extends Error[String]

  case object StringWriter extends Writer[String]

  @main def main(): Unit =

    val program =
      for
        number <- IntReader.ask
        items <- StringListReader.ask
        randomItems <-
          if items.sizeIs >= number
          then !!.impure(util.Random.shuffle(items).take(number).mkString(", "))
          else StringError.raise("Incorrect number")
        _ <- StringWriter.tell(s"random items: $randomItems")
      yield randomItems

    val result =
      program
        .handleWith(IntReader.handler(3))
        .handleWith(StringListReader.handler(List("1", "2", "3", "4")))
        .handleWith(StringError.handler)
        .handleWith(StringWriter.handler)
        .run

    println(result)
}
