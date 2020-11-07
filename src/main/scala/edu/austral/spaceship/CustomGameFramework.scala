package edu.austral.spaceship

import edu.austral.spaceship.base.framework.{GameFramework, ImageLoader, WindowSettings}
import processing.core.PGraphics
import processing.event.KeyEvent

object CustomGameFramework extends GameFramework {


  var keysDown: Set[Char] = Set()

  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {

  }

  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Int]): Unit = {

  }

  override def keyPressed(event: KeyEvent): Unit = {
    keysDown += event.getKey
  }

  override def keyReleased(event: KeyEvent): Unit = {
    keysDown -= event.getKey
  }
}
