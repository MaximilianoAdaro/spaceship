package edu.austral.spaceship.base.model.sprites

import java.awt.Shape
import java.awt.geom.Ellipse2D

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.model.{ShootStrategy, Sprite}
import edu.austral.spaceship.base.util.Vector2
import edu.austral.spaceship.base.view.ImageProvider
import processing.core.PImage

case class Weapon(weaponType: WeaponType, position: Vector2, starship: Starship, speed: Vector2) extends Sprite with Collisionable {

  override def getShape: Shape = new Ellipse2D.Float(position.x, position.y, weaponType.size * 2, weaponType.size * 2)

  override def getImage(visitor: ImageProvider): PImage = visitor.getImage(weaponType.name, weaponType.size)

  override def getPosition: Vector2 = position

  override def getSpeed: Vector2 = speed
}

case class WeaponType(name: String, bulletType: BulletType, shootStrategy: ShootStrategy, fireRate: Float, size: Int) {

  def shoot(bulletType: BulletType, position: Vector2, starship: Starship, speed: Vector2, timeCreated: Long): List[Bullet] = {
    shootStrategy.shoot(bulletType, position, starship, speed, timeCreated)
  }

}