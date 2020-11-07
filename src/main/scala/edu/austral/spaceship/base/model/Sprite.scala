package edu.austral.spaceship.base.model

import edu.austral.spaceship.base.util.Vector2
import edu.austral.spaceship.base.view.ImageProvider
import processing.core.PImage

trait Sprite {

  def getImage(visitor: ImageProvider): PImage

  def getPosition: Vector2

  def getSpeed: Vector2

}
