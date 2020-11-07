package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.GameSprites
import edu.austral.spaceship.base.model.sprites.{Asteroid, AsteroidType}

import scala.util.Random

object AsteroidEngine extends Engine[Asteroid] {

  var asteroidTypes: List[AsteroidType] = List(
    AsteroidType("ASTEROID_SMALL", 1, 50, 25),
    AsteroidType("ASTEROID_BIG", 1, 25, 50)
  )

  def getAnAsteroidType: AsteroidType = asteroidTypes(Random.nextInt(2))

  override def plusTime(gameState: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[Asteroid] = {
    ???
  }

}
