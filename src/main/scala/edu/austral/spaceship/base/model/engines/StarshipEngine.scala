package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.sprites.{Starship, StarshipType}
import edu.austral.spaceship.base.model.{GameSprites, Player}

import scala.util.Random

object StarshipEngine extends Engine[Starship] {

  var starshipTypes: List[StarshipType] = List(
    StarshipType("STARSHIP_SMALL", WeaponEngine.getAWeaponType, 50),
    StarshipType("STARSHIP_BIG", WeaponEngine.getAWeaponType, 60)
  )

  def getStarshipType: StarshipType = starshipTypes(Random.nextInt(2))

  override def plusTime(gameState: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[Starship] = {
    ???
  }

}
