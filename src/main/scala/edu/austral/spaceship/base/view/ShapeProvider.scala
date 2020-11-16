package edu.austral.spaceship.base.view

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.framework.ImageLoader
import edu.austral.spaceship.base.model.Sprite
import processing.core.PConstants

object ShapeProvider {

  val imageProvider: ImageProvider = new ImageProvider

  def loadShapes(imageLoader: ImageLoader): Unit = {
    imageProvider.setImages(
      Map(
        "ASTEROID_SMALL" -> imageLoader.load("asteroid.png"),
        "ASTEROID_BIG" -> imageLoader.load("asteroid.png"),
        "BULLET_SMALL" -> imageLoader.load("bullet1.png"),
        "BULLET_BIG" -> imageLoader.load("bullet1.png"),
        "STARSHIP_SMALL" -> imageLoader.load("starship.png"),
        "STARSHIP_BIG" -> imageLoader.load("starship.png"),
        "GUN_SMALL" -> imageLoader.load("gun2.png"),
        "GUN_BIG" -> imageLoader.load("gun2.png"),
        "BACKGROUND" -> imageLoader.load("background.png"),
        "BACKGROUND_2" -> imageLoader.load("background.jpg")
      )
    )
  }

  def provideShape(sprite: Sprite with Collisionable): Drawable = {
    Drawable(
      sprite.getImage(imageProvider),
      sprite.getPosition.x,
      sprite.getPosition.y,
      sprite.getSpeed.rotate(PConstants.PI / 2).angle,
      sprite.getSpeed.module,
      sprite.getShape
    )
  }

}
