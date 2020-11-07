package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.GameSprites
import edu.austral.spaceship.base.model.sprites.{Bullet, BulletType}

import scala.util.Random

object BulletEngine extends Engine[Bullet] {

  var bulletTypes: List[BulletType] = List(
    BulletType("BULLET_SMALL", 50, 20),
    BulletType("BULLET_BIG", 100, 25)
  )

  def getABulletType: BulletType = bulletTypes(Random.nextInt(2))

  override def plusTime(gameState: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[Bullet] = {
    ???
  }

}
