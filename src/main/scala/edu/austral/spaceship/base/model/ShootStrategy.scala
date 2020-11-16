package edu.austral.spaceship.base.model

import edu.austral.spaceship.base.model.sprites.{Bullet, BulletType, Starship}
import edu.austral.spaceship.base.util.Vector2

import scala.util.Random

trait ShootStrategy {

  def shoot(bulletType: BulletType, position: Vector2, starship: Starship, speed: Vector2, timeCreated: Long): List[Bullet]

}

case object SingleShot extends ShootStrategy {

  override def shoot(bulletType: BulletType, position: Vector2, starship: Starship, speed: Vector2, timeCreated: Long): List[Bullet] = {
    List(Bullet(bulletType, position, starship, speed, timeCreated))
  }

}

case object MultiShot extends ShootStrategy {


  override def shoot(bulletType: BulletType, position: Vector2, starship: Starship, speed: Vector2, timeCreated: Long): List[Bullet] = {
    twoBullets(bulletType, position, starship, speed, timeCreated)
  }

  private def twoBullets(bulletType: BulletType, position: Vector2, starship: Starship, speed: Vector2, timeCreated: Long): List[Bullet] = {
    List(
      Bullet(bulletType, position, starship, speed, timeCreated),
      Bullet(bulletType, randomBulletPosition(position), starship, speed, timeCreated),
    )
  }

  private def randomBulletPosition(position: Vector2): Vector2 = {
    val x = Random.between(30, 40)
    val y = Random.between(30, 40)

    Random.nextInt(4) match {
      case 0 => Vector2(position.x - x, position.y + y)
      case 1 => Vector2(position.x - x, position.y - y)
      case 2 => Vector2(position.x + x, position.y + y)
      case 3 => Vector2(position.x + x, position.y - y)
    }
  }
}