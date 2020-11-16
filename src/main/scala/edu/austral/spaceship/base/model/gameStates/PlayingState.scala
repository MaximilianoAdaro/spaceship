package edu.austral.spaceship.base.model.gameStates

import edu.austral.spaceship.base.collision.Collisionable
import edu.austral.spaceship.base.model.{LivesCounter, ScoreCounter, SpaceshipGameModel, Sprite}
import edu.austral.spaceship.base.view.{ImageProvider, ShapeProvider}
import processing.core.PGraphics

case class PlayingState() extends GameState {

  override def next(model: SpaceshipGameModel, pGraphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Char]): GameState = {
    model.nextCycle(keySet)
    if (model.gameSprites.starships.nonEmpty) {
      val sprites = model.getSprites
      printBackground(pGraphics, model.maxX, model.maxY)
      printAllSprites(sprites, pGraphics)
      printScores(pGraphics)
      printLives(pGraphics)
      this
    }
    else new FinishedState
  }

  def printBackground(pGraphics: PGraphics, maxX: Int, maxY: Int): Unit = {
    pGraphics.background(ImageProvider.getImageByName("BACKGROUND", maxX, maxY))
  }

  def printAllSprites(sprites: List[Sprite with Collisionable], pGraphics: PGraphics): Unit = {
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
      case (player, index) => pGraphics.text(player, 300, 100 + index * 12)
    }
  }

}
