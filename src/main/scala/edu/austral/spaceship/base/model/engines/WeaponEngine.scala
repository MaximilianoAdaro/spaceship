package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.sprites.{Weapon, WeaponType}
import edu.austral.spaceship.base.model.{GameSprites, MultiShot, ShootStrategy, SingleShot}
import edu.austral.spaceship.base.util.Vector2

import scala.util.Random

object WeaponEngine extends Engine[Weapon] {

  var weaponTypes: List[WeaponType] = List(
    WeaponType("GUN_SMALL", BulletEngine.getABulletType, getAShootStrategy, 1, 15),
    WeaponType("GUN_BIG", BulletEngine.getABulletType, getAShootStrategy, 3, 20),
    WeaponType("GUN_BIG", BulletEngine.getABulletType, getAShootStrategy, 7, 25),
  )

  def getAWeaponType: WeaponType = weaponTypes(Random.nextInt(3))

  def getAShootStrategy: ShootStrategy = List(SingleShot, MultiShot)(Random.nextInt(2))

  override def nextCycle(gameSprites: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[Weapon] = {
    gameSprites.starships.map(starship => {
      Weapon(starship.starshipType.weaponType, starship.position + Vector2(-25, 35), starship, starship.speed)
    })
  }

}
