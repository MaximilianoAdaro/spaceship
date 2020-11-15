package edu.austral.spaceship.base.view

import edu.austral.spaceship.base.model.Sprite
import edu.austral.spaceship.base.view.ImageProvider.resizeImage
import edu.austral.spaceship.base.view.ShapeProvider.imageProvider.images
import processing.core.PImage

class ImageProvider {
  var images: Map[String, PImage] = Map()

  def setImages(map: Map[String, PImage]): Unit = images = map

  def getImage(sprite: Sprite): PImage = sprite.getImage(this)

  def getImage(imageName: String, size: Int): PImage = resizeImage(images(imageName), size, size)


}

object ImageProvider {
  def getImageByName(imageName: String, sizeX: Int, sizeY: Int): PImage = {
    resizeImage(images(imageName), sizeX, sizeY)
  }

  def resizeImage(image: PImage, sizeX: Int, sizeY: Int): PImage = {
    image.resize(sizeX, sizeY)
    image
  }
}