package edu.austral.spaceship.base.model.sprites

import java.awt.Shape
import java.awt.geom.Ellipse2D

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.model.Sprite
import edu.austral.spaceship.base.util.Vector2
import edu.austral.spaceship.base.view.ImageProvider
import processing.core.PImage

case class Asteroid(asteroidType: AsteroidType, position: Vector2, speed: Vector2) extends Sprite with Collisionable {

  override def getShape: Shape = new Ellipse2D.Float(position.x, position.y, asteroidType.size * 2, asteroidType.size * 2)

  override def getImage(visitor: ImageProvider): PImage = visitor.getImage(asteroidType.name, asteroidType.size)

  override def getPosition: Vector2 = position

  override def getSpeed: Vector2 = speed
}

case class AsteroidType(name: String, health: Int, points: Int, size: Int)