package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.{GameSprites, ScoreCounter}
import edu.austral.spaceship.base.model.sprites.{Asteroid, AsteroidType, Bullet}
import edu.austral.spaceship.base.util.Vector2

import scala.util.Random

object AsteroidEngine extends Engine[Asteroid] {

  var asteroidTypes: List[AsteroidType] = List(
    AsteroidType("ASTEROID_SMALL", Random.between(30, 90), 50, 40),
    AsteroidType("ASTEROID_BIG", Random.between(70, 120), 25, 60)
  )

  def getAnAsteroidType: AsteroidType = asteroidTypes(Random.nextInt(2))

  override def nextCycle(gameSprites: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[Asteroid] = {
    gameSprites.asteroids.flatMap(plusTimeAsteroid(_, gameSprites.bullets, maxX, maxY)) ::: newAsteroid(maxX, maxY)
  }

  def plusTimeAsteroid(asteroid: Asteroid, bullets: List[Bullet], maxX: Int, maxY: Int): Option[Asteroid] = {
    bullets.foreach(bullet => {
      val bDamage = bullet.bulletType.damage
      if (bullet.collidesWith(asteroid)) {
        asteroid.asteroidType.health match {
          case h if (h - asteroid.damageReceived) > bDamage =>
            return Some(asteroid.copy(position = asteroid.position + asteroid.speed, damageReceived = bDamage + asteroid.damageReceived))
          case _ =>
            ScoreCounter.onBulletCollisionWithAsteroid(bullet, asteroid.asteroidType.points)
            return None
        }
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
