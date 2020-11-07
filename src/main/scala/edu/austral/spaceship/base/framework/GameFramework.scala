package edu.austral.spaceship.base.framework

import processing.core.PGraphics
import processing.event.KeyEvent

trait GameFramework {

  def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit

  def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Char]): Unit

  def keyPressed(event: KeyEvent): Unit

  def keyReleased(event: KeyEvent): Unit

}
