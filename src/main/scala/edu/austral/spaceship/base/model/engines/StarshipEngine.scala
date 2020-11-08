package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.sprites.{Starship, StarshipType}
import edu.austral.spaceship.base.model.{GameSprites, LivesCounter, ScoreCounter}
import edu.austral.spaceship.base.util.Vector2

import scala.util.Random

object StarshipEngine extends Engine[Starship] {

  var starshipTypes: List[StarshipType] = List(
    StarshipType("STARSHIP_SMALL", WeaponEngine.getAWeaponType, 50),
    StarshipType("STARSHIP_BIG", WeaponEngine.getAWeaponType, 60)
  )

  def getStarshipType: StarshipType = starshipTypes(Random.nextInt(2))

  override def plusTime(gameSprites: GameSprites, keysDown: Set[Char], maxX: Int, maxY: Int): List[Starship] = {
    gameSprites.starships.flatMap(starship => {
      val newStarship = plusTimeStarship(starship, keysDown, maxX, maxY)
      addPoints(gameSprites, newStarship)
      checkDead(gameSprites, newStarship)
    })
  }

  def plusTimeStarship(starship: Starship, keysDown: Set[Char], maxX: Int, maxY: Int): Starship = {
    val newStarship = checkBorders(processKeys(starship, keysDown), maxX, maxY)
    newStarship.copy(position = newStarship.position + newStarship.speed)
  }

  def addPoints(gameSprites: GameSprites, starship: Starship): Unit = {
    gameSprites.bullets.filter(_.starship.player != starship.player).foreach(bullet => {
      if (starship.collidesWith(bullet)) ScoreCounter.onBulletCollisionWithStarship(bullet)
    })
  }

  def checkDead(gameSprites: GameSprites, starship: Starship): Option[Starship] = {
    val otherBullets = gameSprites.bullets.filter(_.starship.player != starship.player)
    val playerName = starship.player.name
    val lives = LivesCounter.getLivesByPlayer(playerName)
    val notDied = lives <= 0

    if (notDied && starship.collidesWithAny(gameSprites.asteroids ::: otherBullets)) {
      LivesCounter.onLifeLost(playerName)

      if (lives <= 1) {
        ScoreCounter.reset(playerName)
        None
      }
    }
    Some(starship)
  }

  def processKeys(starship: Starship, keysDown: Set[Char]): Starship = {
    var deltaX: Float = 0
    var deltaY: Float = 0
    val speedDelta = 0.5F

    keysDown.foreach {
      case starship.player.controls.upKey => deltaY = deltaY - speedDelta
      case starship.player.controls.leftKey => deltaX = deltaX - speedDelta
      case starship.player.controls.downKey => deltaY = deltaY + speedDelta
      case starship.player.controls.rightKey => deltaX = deltaX + speedDelta
      case _ =>
    }

    starship.copy(speed = Vector2(starship.speed.x + deltaX, starship.speed.y + deltaY))
  }

  def checkBorders(starship: Starship, maxX: Int, maxY: Int): Starship = {
    var xSpeed = starship.speed.x
    var ySpeed = starship.speed.y

    starship.position.x match {
      case x if x < 50 => xSpeed = 5
      case x if x > maxX - 50 => xSpeed = -5
      case _ =>
    }
    starship.position.y match {
      case y if y < 50 => ySpeed = 5
      case y if y > maxY + 50 => ySpeed = -5
      case _ =>
    }
    starship.copy(speed = Vector2(xSpeed, ySpeed))
  }

}
