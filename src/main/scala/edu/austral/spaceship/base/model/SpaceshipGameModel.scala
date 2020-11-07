package edu.austral.spaceship.base.model

class SpaceshipGameModel {

  var maxX: Int = 0
  var maxY: Int = 0
  var gameSprites: GameSprites = GameSprites(List(), List(), List(), List())
  var players: List[Player] = List()
  var maxLives: Int = 0

  def init(players: List[Player], maxX: Int, maxY: Int, maxLives: Int): Unit = {
    this.maxLives = maxLives
    this.players = players
    this.maxX = maxX
    this.maxY = maxY

  }

  def nextCycle(keyDown: Set[Char]): GameSprites = {
    ???
  }

  def getSprites: List[Sprite] = gameSprites.starships ::: gameSprites.asteroids ::: gameSprites.bullets ::: gameSprites.weapons
}
