package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.model.GameSprites
import edu.austral.spaceship.base.model.sprites.{Bullet, BulletType, Starship}

import scala.util.Random

object BulletEngine extends Engine[Bullet] {

  var bulletTypes: List[BulletType] = List(
    BulletType("BULLET_SMALL", 50, 30),
    BulletType("BULLET_BIG", 100, 35),
    BulletType("BULLET_BIG", 150, 40),
  )

  def getABulletType: BulletType = bulletTypes(Random.nextInt(3))

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
        newBullet(starship)
      } else None
    })
  }

  // Each starship has a cooler time
  def canShoot(starship: Starship, gameSprites: GameSprites): Boolean = {
    val theirBullets = gameSprites.bullets.filter(bullet => bullet.starship.player == starship.player)
    val coolerTime = 1000.0
    if (theirBullets.isEmpty) {
      true
    } else {
      val lastBulletLifeTime = theirBullets.minBy(bullet => bullet.lifeTime).lifeTime
      (coolerTime / starship.starshipType.weaponType.fireRate) <= lastBulletLifeTime
    }
  }

  def newBullet(starship: Starship): List[Bullet] = {
    val bulletType = starship.starshipType.weaponType.bulletType
    val position = starship.position
    val speed = starship.speed.unitary * 15
    val time = System.currentTimeMillis()
    starship.starshipType.weaponType.shoot(bulletType, position, starship, speed, time)
  }
}
