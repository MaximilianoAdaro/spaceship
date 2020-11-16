package edu.austral.spaceship

import edu.austral.spaceship.base.framework.{GameFramework, ImageLoader, WindowSettings}
import edu.austral.spaceship.base.model.gameStates.{GameState, PlayingState}
import edu.austral.spaceship.base.model.{Controls, Player, SpaceshipGameModel}
import edu.austral.spaceship.base.util.Config
import edu.austral.spaceship.base.view.ShapeProvider
import processing.core.PGraphics
import processing.event.KeyEvent

object CustomGameFramework extends GameFramework {

  var model: SpaceshipGameModel = new SpaceshipGameModel
  var keysDown: Set[Char] = Set()
  var gameState: GameState = new PlayingState

  var maxX = 0
  var maxY = 0
  var maxLives = 0


  override def setup(windowsSettings: WindowSettings, imageLoader: ImageLoader): Unit = {
    Config.init("/starship.props")
    ShapeProvider.loadShapes(imageLoader)
    maxX = Config.getIntegerProperty("maxX")
    maxY = Config.getIntegerProperty("maxY")
    maxLives = Config.getIntegerProperty("maxLives")
    println(maxX, maxY, maxLives)
    windowsSettings.setSize(maxX, maxY)
    initGameModel()
  }

  def initGameModel(): Unit = {
    model = new SpaceshipGameModel
    model.init(buildPlayers(), maxX, maxY, maxLives)
  }

  def buildPlayers(): List[Player] = {
    List.range(1, Config.getIntegerProperty("players") + 1)
      .map(playerNumber => {
        Player(
          Config.getProperty("playerName" + playerNumber),
          Controls(
            Config.getCharProperty("up" + playerNumber),
            Config.getCharProperty("down" + playerNumber),
            Config.getCharProperty("left" + playerNumber),
            Config.getCharProperty("right" + playerNumber),
            Config.getCharProperty("shoot" + playerNumber)
          )
        )
      }
      )
  }

  override def draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Int]): Unit = {
    gameState = gameState.next(model, graphics, timeSinceLastDraw, keysDown)
  }

  override def keyPressed(event: KeyEvent): Unit = {
    keysDown += event.getKey
  }

  override def keyReleased(event: KeyEvent): Unit = {
    keysDown -= event.getKey
  }
}
