package edu.austral.spaceship.base.model.gameStates

import edu.austral.spaceship.CustomGameFramework
import edu.austral.spaceship.base.model.{ScoreCounter, SpaceshipGameModel}
import processing.core.PGraphics

case class FinishedState() extends GameState {

  var runtime: Long = 0
  val timeShown = 4000

  override def draw(model: SpaceshipGameModel, PGraphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Char]): GameState = {
    ScoreCounter.getScores.foreach(score => {
      PGraphics.fill(255, 0, 0)
      PGraphics.text("Finished", model.maxX / 2, model.maxY / 2 - 30)
      PGraphics.text(score, model.maxX / 2, model.maxY / 2)
      PGraphics.fill(255, 255, 255)
    })
    if (runtime == 0) {
      runtime = System.currentTimeMillis()
    } else if (runtime + timeShown < System.currentTimeMillis()) {
      CustomGameFramework.initGameModel()
      return new PlayingState
    }
    this
  }

}
