package cpsEffect.examples



trait TaglessEffectSystem {

   type Effect[F[_]]


}



object RandomWinner {


  def selectWinner[F[_]: FileReader, RandomGenerator](file: String, nWinners): F[String] = {
      
     
  }


  def main() {

    selectWinner(?)

  }


}
