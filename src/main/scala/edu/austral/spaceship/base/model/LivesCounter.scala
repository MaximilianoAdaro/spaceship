package edu.austral.spaceship.base.model

object LivesCounter {

  var livesPlayers: Map[String, Int] = Map()
  var maxLives = 0

  def init(players: List[String], maxLives: Int): Unit = {
    this.maxLives = maxLives
    players.foreach(players => livesPlayers = livesPlayers.updated(players, maxLives))
  }

  def onLifeLost(playerName: String): Unit = {
    livesPlayers = livesPlayers.updatedWith(playerName)(oldValue => oldValue.map(value => value - 1))
  }

  def getLives: List[String] = {
    livesPlayers.toList.map { case (name: String, value: Int) => name + ": " + value + "♥" }
  }

  def getLivesByPlayer(playerName: String): Int = {
    livesPlayers(playerName)
  }

  def reset(name: String): Unit = {
    livesPlayers = livesPlayers.updated(name, maxLives)
  }
}
