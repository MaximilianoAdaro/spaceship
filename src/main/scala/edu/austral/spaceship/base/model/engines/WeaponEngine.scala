package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.GameSprites
import edu.austral.spaceship.base.model.sprites.{Weapon, WeaponType}
import edu.austral.spaceship.base.util.Vector2

import scala.util.Random

object WeaponEngine extends Engine[Weapon] {

  var weaponTypes: List[WeaponType] = List(
    WeaponType("GUN_SMALL", BulletEngine.getABulletType, 1, 25),
    WeaponType("GUN_BIG", BulletEngine.getABulletType, 5, 25)
  )

  def getAWeaponType: WeaponType = weaponTypes(Random.nextInt(2))

  override def plusTime(gameSprites: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[Weapon] = {
    gameSprites.starships.map(starship => {
      Weapon(starship.starshipType.weaponType, starship.position + Vector2(-25, 35), starship, starship.speed)
    })
  }

}
