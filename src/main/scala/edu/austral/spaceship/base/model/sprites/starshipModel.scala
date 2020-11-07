package edu.austral.spaceship.base.model.sprites

import java.awt.Shape

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.model.{Player, Sprite}
import edu.austral.spaceship.base.util.Vector2
import edu.austral.spaceship.base.view.ImageProvider
import processing.core.PImage

case class Starship(starshipType: StarshipType, position: Vector2, player: Player, maxLives: Int, speed: Vector2) extends Sprite with Collisionable {

  override def getShape: Shape = starshipType.shape

  override def getImage(visitor: ImageProvider): PImage = visitor.getImage(starshipType.name)

  override def getPosition: Vector2 = position

  override def getSpeed: Vector2 = speed
}

case class StarshipType(name: String, weaponType: WeaponType, shape: Shape)