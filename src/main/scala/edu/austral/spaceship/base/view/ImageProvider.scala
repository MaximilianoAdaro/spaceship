package edu.austral.spaceship.base.view

import edu.austral.spaceship.base.model.Sprite
import edu.austral.spaceship.base.view.ShapeProvider.imageProvider.images
import processing.core.PImage

class ImageProvider {
  var images: Map[String, PImage] = Map()

  def setImages(map: Map[String, PImage]): Unit = images = map

  def getImage(sprite: Sprite): PImage = sprite.getImage(this)

  def getImage(imageName: String, size: Int): PImage = resizeImage(images(imageName), size)

  def resizeImage(image: PImage, size: Int): PImage = {
    image.resize(size, size)
    image
  }
}

object ImageProvider {
  def getImageByName(imageName: String): PImage = {
    images(imageName)
  }
}