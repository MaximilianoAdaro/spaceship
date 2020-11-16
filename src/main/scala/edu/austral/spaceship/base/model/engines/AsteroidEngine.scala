package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.{GameSprites, ScoreCounter}
import edu.austral.spaceship.base.model.sprites.{Asteroid, AsteroidType, Bullet}
import edu.austral.spaceship.base.util.Vector2

import scala.util.Random

object AsteroidEngine extends Engine[Asteroid] {

  var asteroidTypes: List[AsteroidType] = List(
    AsteroidType("ASTEROID_SMALL", 1, 50, 40),
    AsteroidType("ASTEROID_BIG", 1, 25, 60)
  )

  def getAnAsteroidType: AsteroidType = asteroidTypes(Random.nextInt(2))

  override def plusTime(gameSprites: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[Asteroid] = {
    gameSprites.asteroids.flatMap(plusTimeAsteroid(_, gameSprites.bullets, maxX, maxY)) ::: newAsteroid(maxX, maxY)
  }

  def plusTimeAsteroid(asteroid: Asteroid, bullets: List[Bullet], maxX: Int, maxY: Int): Option[Asteroid] = {
    bullets.foreach(bullet => {
      if (bullet.collidesWith(asteroid)) {
        ScoreCounter.onBulletCollisionWithAsteroid(bullet, asteroid.asteroidType.points)
        return None
      }
    })
    if (isInside(asteroid, maxX, maxY))
      Some(asteroid.copy(position = asteroid.position + asteroid.speed))
    else
      None
  }

  def newAsteroid(maxX: Int, maxY: Int): List[Asteroid] = {
    if (Random.nextInt(maxX * maxY / 30000) == 1) List(buildAsteroid(maxX, maxY)) else List()
  }

  def buildAsteroid(maxX: Int, maxY: Int): Asteroid = {
    Random.nextInt(3) match {
      //top side
      case 0 =>
        Asteroid(
          getAnAsteroidType,
          Vector2(Random.nextInt(maxX), 0 + 1),
          Vector2.fromModule(3, Math.toRadians(Random.nextInt(90) + 45).toFloat),
        )
      //right side
      case 1 =>
        Asteroid(
          getAnAsteroidType,
          Vector2(maxX - 1, Random.nextInt(maxY)),
          Vector2.fromModule(3, Math.toRadians(Random.nextInt(90) + 135).toFloat),
        )
      //left side
      case 2 =>
        Asteroid(
          getAnAsteroidType,
          Vector2(0 + 1, Random.nextInt(maxY)),
          Vector2.fromModule(3, Math.toRadians(Random.nextInt(90) - 45).toFloat),
        )
    }
  }

}
