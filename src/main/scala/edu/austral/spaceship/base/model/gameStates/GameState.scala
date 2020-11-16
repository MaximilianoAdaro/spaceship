package edu.austral.spaceship.base.model.gameStates

import edu.austral.spaceship.base.model.SpaceshipGameModel
import processing.core.PGraphics

trait GameState {

  def next(model: SpaceshipGameModel, PGraphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Char]): GameState

}
