package edu.austral.spaceship.base.model.gameStates

import edu.austral.spaceship.base.model.{LivesCounter, ScoreCounter, SpaceshipGameModel, Sprite}
import edu.austral.spaceship.base.view.ShapeProvider
import processing.core.PGraphics

class PlayingState extends GameState {

  override def draw(model: SpaceshipGameModel, pGraphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Char]): GameState = {
    model.nextCycle(keySet)
    if (model.gameSprites.starships.nonEmpty) {
      val sprites = model.getSprites
      printAllSprites(sprites, pGraphics)
      printScores(pGraphics)
      printLives(pGraphics)
    }
    this
  }

  def printAllSprites(sprites: List[Sprite], pGraphics: PGraphics): Unit = {
    sprites
      .map(spritable => ShapeProvider.provideShape(spritable))
      .foreach(drawable => {
        pGraphics.imageMode(3)
        pGraphics.pushMatrix()
        pGraphics.translate(drawable.x, drawable.y)
        pGraphics.rotate(drawable.dir)
        pGraphics.image(drawable.image, 0, 0)
        pGraphics.popMatrix()
      })
  }

  def printScores(pGraphics: PGraphics): Unit = {
    ScoreCounter.getScores.zipWithIndex.foreach {
      case (player, index) => pGraphics.text(player, 100, 100 + index * 12)
    }
  }

  def printLives(pGraphics: PGraphics): Unit = {
    LivesCounter.getLives.zipWithIndex.foreach {
      case (player, index) => pGraphics.text(player, 300, 300 + index * 12)
    }
  }

}
