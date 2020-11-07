package edu.austral.spaceship.base.view

import edu.austral.spaceship.base.model.Sprite
import processing.core.PImage

class ImageProvider {
  var images: Map[String, PImage] = Map()

  def setImages(map: Map[String, PImage]): Unit = images = map

  def getImage(sprite: Sprite): PImage = sprite.getImage(this)

  def getImage(imageName: String): PImage = images(imageName)

}
