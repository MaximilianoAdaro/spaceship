package edu.austral.spaceship.base.framework

import processing.core.{PApplet, PImage}

case class ImageLoader(applet: PApplet) {

  def load(fileName: String): PImage = applet.loadImage(fileName)

}
