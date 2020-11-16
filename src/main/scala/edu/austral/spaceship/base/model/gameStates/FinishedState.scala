package edu.austral.spaceship.base.model.gameStates

import edu.austral.spaceship.CustomGameFramework
import edu.austral.spaceship.base.model.{ScoreCounter, SpaceshipGameModel}
import edu.austral.spaceship.base.view.ImageProvider
import processing.core.PGraphics

case class FinishedState() extends GameState {

  var runtime: Long = 0
  val timeShown = 4000

  override def next(model: SpaceshipGameModel, PGraphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Char]): GameState = {
    printScore(model, PGraphics)
    if (runtime == 0) {
      runtime = System.currentTimeMillis()
    } else if (runtime + timeShown < System.currentTimeMillis()) {
      CustomGameFramework.initGameModel()
      return new PlayingState
    }
    this
  }

  def printScore(model: SpaceshipGameModel, pGraphics: PGraphics): Unit = {
    printBackground(pGraphics, model.maxX, model.maxY)
    pGraphics.fill(255, 0, 0)
    pGraphics.text("Finished", model.maxX / 2, model.maxY / 2 - 130)
    ScoreCounter.getScores.zipWithIndex.foreach {
      case (score: String, index: Int) => pGraphics.text(score, model.maxX / 2, model.maxY / 2 + index * 12 - 100)
    }
    pGraphics.fill(255, 255, 255)
  }

  def printBackground(pGraphics: PGraphics, maxX: Int, maxY: Int): Unit = {
    pGraphics.background(ImageProvider.getImageByName("BACKGROUND_2", maxX, maxY))
  }
}
