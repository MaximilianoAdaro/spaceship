package edu.austral.spaceship.base.view

import edu.austral.spaceship.base.framework.ImageLoader
import edu.austral.spaceship.base.model.Sprite
import processing.core.PConstants

object ShapeProvider {

  val imageProvider: ImageProvider = new ImageProvider

  def loadShapes(imageLoader: ImageLoader): Unit = {
    imageProvider.setImages(
      Map(
        "ASTEROID_SMALL" -> imageLoader.load("asteroid.gif"),
        "ASTEROID_BIG" -> imageLoader.load("asteroid.gif"),
        "BULLET_SMALL" -> imageLoader.load("bullet.png"),
        "BULLET_BIG" -> imageLoader.load("bullet.png"),
        "STARSHIP_SMALL" -> imageLoader.load("starship.png"),
        "STARSHIP_BIG" -> imageLoader.load("starship.png"),
        "GUN_SMALL" -> imageLoader.load("gun.png"),
        "GUN_BIG" -> imageLoader.load("gun.png"),
      )
    )
  }

  def provideShape(sprite: Sprite): Drawable = {
    Drawable(
      sprite.getImage(imageProvider),
      sprite.getPosition.x,
      sprite.getPosition.y,
      sprite.getSpeed.rotate(PConstants.PI / 2).angle,
      sprite.getSpeed.module
    )
  }

}
