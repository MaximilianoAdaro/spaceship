package edu.austral.spaceship.base.model.sprites

import java.awt.Shape

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.model.Sprite
import edu.austral.spaceship.base.util.Vector2
import edu.austral.spaceship.base.view.ImageProvider
import processing.core.PImage

case class Bullet(bulletType: BulletType, position: Vector2, weapon: Weapon, speed: Vector2) extends Sprite with Collisionable {

  override def getShape: Shape = bulletType.shape

  override def getImage(visitor: ImageProvider): PImage = ???

  override def getPosition: Vector2 = position

  override def getSpeed: Vector2 = speed
}

case class BulletType(name: String, damage: Int, shape: Shape)