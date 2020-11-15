package edu.austral.spaceship.base.model.gameStates

import edu.austral.spaceship.CustomGameFramework
import edu.austral.spaceship.base.model.{ScoreCounter, SpaceshipGameModel}
import processing.core.PGraphics

case class FinishedState() extends GameState {

  var runtime: Long = 0
  val timeShown = 4000

  override def draw(model: SpaceshipGameModel, PGraphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Char]): GameState = {
    printScore(model, PGraphics)
    if (runtime == 0) {
      runtime = System.currentTimeMillis()
    } else if (runtime + timeShown < System.currentTimeMillis()) {
      CustomGameFramework.initGameModel()
      return new PlayingState
    }
    this
  }

  private def printScore(model: SpaceshipGameModel, PGraphics: PGraphics): Unit = {
    PGraphics.fill(255, 0, 0)
    PGraphics.text("Finished", model.maxX / 2, model.maxY / 2 - 30)
    ScoreCounter.getScores.zipWithIndex.foreach {
      case (score: String, index: Int) => PGraphics.text(score, model.maxX / 2, model.maxY / 2 + index * 12)
    }
    PGraphics.fill(255, 255, 255)
  }
}
