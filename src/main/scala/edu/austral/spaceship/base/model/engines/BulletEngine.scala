package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.model.GameSprites
import edu.austral.spaceship.base.model.sprites.{Bullet, BulletType, Starship}

import scala.util.Random

object BulletEngine extends Engine[Bullet] {

  var bulletTypes: List[BulletType] = List(
    BulletType("BULLET_SMALL", 50, 20),
    BulletType("BULLET_BIG", 100, 25)
  )

  def getABulletType: BulletType = bulletTypes(Random.nextInt(2))

  override def plusTime(gameSprites: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[Bullet] = {
    gameSprites.bullets.flatMap(bullet => plusTimeBullet(gameSprites, bullet, maxX, maxY)) ::: newBullets(gameSprites, keysDown)
  }

  def plusTimeBullet(gameSprites: GameSprites, bullet: Bullet, maxX: Int, maxY: Int): Option[Bullet] = {
    if (isInside(bullet, maxX, maxY) && !collideWithAny(gameSprites, bullet))
      Some(bullet.copy(position = bullet.position + bullet.speed))
    else
      None
  }

  def collideWithAny(gameSprites: GameSprites, bullet: Bullet): Boolean = {
    val possibleCollision: List[Collisionable] = gameSprites.asteroids :::
      gameSprites.starships.filter(starship => starship.player != bullet.starship.player)
    bullet.collidesWithAny(possibleCollision)
  }


  def newBullets(gameSprites: GameSprites, keysDown: Set[Char]): List[Bullet] = {
    gameSprites.starships.flatMap(starship => {
      if (keysDown.contains(starship.player.controls.shootKey) && canShoot(starship, gameSprites)) {
        Some(newBullet(starship))
      } else None
    })
  }

  def canShoot(starship: Starship, gameSprites: GameSprites): Boolean = {
    val theirBullets = gameSprites.bullets.filter(bullet => bullet.starship.player == starship.player)
    if (theirBullets.isEmpty) {
      true
    } else {
      val lastBulletLifeTime = theirBullets.minBy(bullet => bullet.lifeTime).lifeTime
      (1000.0 / starship.starshipType.weaponType.fireRate) <= lastBulletLifeTime //todo: revisar esto}
    }
  }

  def newBullet(starship: Starship): Bullet = {
    Bullet(starship.starshipType.weaponType.bulletType, starship.position, starship, starship.speed.unitary * 15, System.currentTimeMillis())
  }
}
