package edu.austral.spaceship.base.view

import edu.austral.spaceship.base.framework.ImageLoader
import edu.austral.spaceship.base.model.Sprite

object ShapeProvider {

  val imageProvider: ImageProvider = new ImageProvider

  def loadShapes(imageLoader: ImageLoader): Unit = {

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
