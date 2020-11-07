package edu.austral.spaceship.base.view

import edu.austral.spaceship.base.framework.ImageLoader
import edu.austral.spaceship.base.model.Sprite

object ShapeProvider {

  val imageProvider: ImageProvider = new ImageProvider

  def loadShapes(imageLoader: ImageLoader): Unit = {
    imageProvider.setImages(
      Map(
        "ASTEROID_SMALL" -> imageLoader.load("ASTEROID_SMALL"),
        "ASTEROID_BIG" -> imageLoader.load("ASTEROID_BIG"),
        "BULLET_SMALL" -> imageLoader.load("BULLET_SMALL"),
        "BULLET_BIG" -> imageLoader.load("BULLET_BIG"),
        "STARSHIP_SMALL" -> imageLoader.load("STARSHIP_SMALL"),
        "STARSHIP_BIG" -> imageLoader.load("STARSHIP_BIG"),
        "GUN_SMALL" -> imageLoader.load("GUN_SMALL"),
        "GUN_BIG" -> imageLoader.load("GUN_BIG"),
      )
    )
  }

  def provideShape(sprite: Sprite): Drawable = {
    Drawable(
      sprite.getImage(imageProvider),
      sprite.getPosition.x,
      sprite.getPosition.y,
      sprite.getSpeed.angle,
      sprite.getSpeed.module
    )
  }

}
