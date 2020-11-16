package edu.austral.spaceship.base.model.sprites

import java.awt.Shape
import java.awt.geom.Ellipse2D

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.model.Sprite
import edu.austral.spaceship.base.util.Vector2
import edu.austral.spaceship.base.view.ImageProvider
import processing.core.PImage

case class Bullet(bulletType: BulletType, position: Vector2, starship: Starship, speed: Vector2, timeCreated: Long) extends Sprite with Collisionable {

  override def getShape: Shape = new Ellipse2D.Float(position.x, position.y, bulletType.size * 3 / 2, bulletType.size * 3 / 2)

  override def getImage(visitor: ImageProvider): PImage = visitor.getImage(bulletType.name, bulletType.size)

  override def getPosition: Vector2 = position

  override def getSpeed: Vector2 = speed

  def lifeTime: Long = System.currentTimeMillis() - timeCreated
}

case class BulletType(name: String, damage: Int, size: Int)