package edu.austral.spaceship

import edu.austral.spaceship.CustomGameFramework.model.{maxX, maxY}
import edu.austral.spaceship.base.framework.{GameFramework, ImageLoader, WindowSettings}
import edu.austral.spaceship.base.model.{Player, SpaceshipGameModel}
import edu.austral.spaceship.base.model.gameStates.{GameState, PlayingState}
import edu.austral.spaceship.base.view.ShapeProvider
import processing.core.PGraphics
import processing.event.KeyEvent

object CustomGameFramework extends GameFramework {

  val model: SpaceshipGameModel = new SpaceshipGameModel
  var keysDown: Set[Char] = Set()
  var gameState: GameState = new PlayingState

  val maxX = 1500
  val maxY = 1000
  val maxLives = 3

  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {
    ShapeProvider.loadShapes(imageLoader)
    windowsSettings.setSize(maxX, maxY)
    model.init(
      buildPlayers(),
      maxX,
      maxY,
      maxLives
    )
  }

  def buildPlayers(): List[Player] = ???

  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Char]): Unit = {
    gameState = gameState.draw(model, graphics, timeSinceLastDraw, keysDown)
  }

  override def keyPressed(event: KeyEvent): Unit = {
    keysDown += event.getKey
  }

  override def keyReleased(event: KeyEvent): Unit = {
    keysDown -= event.getKey
  }
}
