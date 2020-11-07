package edu.austral.spaceship.base.model

object LivesCounter {

  var livesPlayers: Map[String, Int] = Map()

  def init(players: List[String], maxLives: Int): Unit = {
    players.foreach(players => livesPlayers = livesPlayers.updated(players, maxLives))
  }

  def onLifeLost(playerName: String): Unit = {
    livesPlayers = livesPlayers.updatedWith(playerName)(oldValue => oldValue.map(value => value - 1))
  }

  def getLives: List[String] = {
    livesPlayers.toList.map { case (name: String, value: Int) => name + ": " + value + "♥" }
  }
}
