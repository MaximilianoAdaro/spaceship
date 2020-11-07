package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.{GameSprites, Sprite}

trait Engine[T] {

  def plusTime(gameState: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[T]

  def isInside(spritable: Sprite, maxX: Int, maxY: Int): Boolean = {
    spritable.getPosition.x > 0 && spritable.getPosition.x < maxX && spritable.getPosition.y + 30 > 0 && spritable.getPosition.y < maxY
  }

}
