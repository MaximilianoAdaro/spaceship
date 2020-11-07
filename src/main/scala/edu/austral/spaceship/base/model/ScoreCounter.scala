package edu.austral.spaceship.base.model

object ScoreCounter {

  var scores: Map[String, Int] = Map()

  def init(players: List[Player]): Unit = players.foreach(player => scores = scores.updated(player.name, 0))

  def getAll: List[String] = {
    scores.toList.map { case (name: String, value: Int) => name + ": " + value }
  }

}
