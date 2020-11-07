package edu.austral.spaceship.base.model.gameStates

import edu.austral.spaceship.base.model.{ScoreCounter, SpaceshipGameModel, Sprite}
import edu.austral.spaceship.base.view.ShapeProvider
import processing.core.PGraphics

class PlayingState extends GameState {

  override def draw(model: SpaceshipGameModel, pGraphics: PGraphics, timeSinceLastDraw: Float, keySet: Set[Char]): GameState = {
    model.nextCycle(keySet)
    if (model.gameSprites.starships.nonEmpty) {
      val sprites = model.getSprites
      printAllSprites(sprites, pGraphics)
      ScoreCounter
        .getAll
        .zipWithIndex
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
        pGraphics.rotate((drawable.dir + Math.PI / 2).toFloat)
        pGraphics.image(drawable.image, 0, 0)
        pGraphics.popMatrix()
      })

  }
}
