package edu.austral.spaceship.base.model

import edu.austral.spaceship.base.model.engines.{AsteroidEngine, BulletEngine, StarshipEngine, WeaponEngine}
import edu.austral.spaceship.base.model.sprites.{Starship, StarshipType}
import edu.austral.spaceship.base.util.{Config, Vector2}

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

    gameSprites = gameSprites.copy(
      starships = players.zipWithIndex.map {
        case (player, index) => Starship(StarshipEngine.getStarshipType, Vector2(maxX / 2, maxY / 2 + index * 100), player, maxLives, Vector2.ZERO)
      }
    )

    LivesCounter.init(players.map(_.name), maxLives)
    ScoreCounter.init(players)
  }

  def nextCycle(keyDown: Set[Char]): Unit = {
    gameSprites = GameSprites(
      StarshipEngine.plusTime(gameSprites, keyDown, maxX, maxY),
      AsteroidEngine.plusTime(gameSprites, keyDown, maxX, maxY),
      WeaponEngine.plusTime(gameSprites, keyDown, maxX, maxY),
      BulletEngine.plusTime(gameSprites, keyDown, maxX, maxY)
    )
  }

  def getSprites: List[Sprite] = gameSprites.starships ::: gameSprites.asteroids ::: gameSprites.bullets ::: gameSprites.weapons

}
