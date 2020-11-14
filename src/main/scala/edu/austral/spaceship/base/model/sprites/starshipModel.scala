package edu.austral.spaceship.base.model.sprites

import java.awt.Shape
import java.awt.geom.Ellipse2D

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.model.{Player, Sprite}
import edu.austral.spaceship.base.util.Vector2
import edu.austral.spaceship.base.view.ImageProvider
import processing.core.PImage

case class Starship(starshipType: StarshipType, position: Vector2, player: Player, maxLives: Int, speed: Vector2, lastDie: Long = 0) extends Sprite with Collisionable {

  override def getShape: Shape = new Ellipse2D.Float(position.x, position.y, starshipType.size * 2, starshipType.size * 2)

  override def getImage(visitor: ImageProvider): PImage = visitor.getImage(starshipType.name, starshipType.size)

  override def getPosition: Vector2 = position

  override def getSpeed: Vector2 = speed
}

case class StarshipType(name: String, weaponType: WeaponType, size: Int)