package edu.austral.spaceship.base.model.engines

import edu.austral.spaceship.base.model.sprites.{Starship, StarshipType}
import edu.austral.spaceship.base.model.{GameSprites, LivesCounter, ScoreCounter}
import edu.austral.spaceship.base.util.Vector2

import scala.util.Random

object StarshipEngine extends Engine[Starship] {

  var starshipTypes: List[StarshipType] = List(
    StarshipType("STARSHIP_SMALL", WeaponEngine.getAWeaponType, 75),
    StarshipType("STARSHIP_BIG", WeaponEngine.getAWeaponType, 100)
  )

  def getStarshipType: StarshipType = {
    val starshipType = starshipTypes(Random.nextInt(2))
    println(starshipType)
    starshipType
  }

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
      if (starship.collidesWith(bullet) && canDieCooler(starship)) ScoreCounter.onBulletCollisionWithStarship(bullet)
    })
  }

  def checkDead(gameSprites: GameSprites, starship: Starship): Option[Starship] = {
    val otherBullets = gameSprites.bullets.filter(_.starship.player != starship.player)
    val playerName = starship.player.name
    val lives = LivesCounter.getLivesByPlayer(playerName)
    val dieTime = canDieCooler(starship)
    val isDied = lives <= 0

    if (!isDied && dieTime && starship.collidesWithAny(gameSprites.asteroids ::: otherBullets)) {
      LivesCounter.onLifeLost(playerName)

      if (lives == 1) {
        None
      } else {
        Some(starship.copy(lastDie = System.currentTimeMillis()))
      }
    }
    else Some(starship)
  }

  private def canDieCooler(starship: Starship): Boolean = {
    starship.lastDie + 1000 < System.currentTimeMillis()
  }

  def processKeys(starship: Starship, keysDown: Set[Char]): Starship = {
    var deltaX: Float = 0
    var deltaY: Float = 0
    val deltaXMax: Float = 10
    val deltaYMax: Float = 10
    val speedDelta = 0.3F

    keysDown.foreach {
      case starship.player.controls.upKey => deltaY = deltaY - speedDelta
      case starship.player.controls.leftKey => deltaX = deltaX - speedDelta
      case starship.player.controls.downKey => deltaY = deltaY + speedDelta
      case starship.player.controls.rightKey => deltaX = deltaX + speedDelta
      case _ =>
    }

    var xSpeed: Float = starship.speed.x + deltaX
    var ySpeed: Float = starship.speed.y + deltaY

    starship.speed.x + deltaX match {
      case x if x > deltaXMax => xSpeed = deltaXMax
      case x if x < -deltaXMax => xSpeed = -deltaXMax
      case _ =>
    }

    starship.speed.y + deltaY match {
      case y if y > deltaYMax => ySpeed = deltaYMax
      case y if y < -deltaYMax => ySpeed = -deltaYMax
      case _ =>
    }

    starship.copy(speed = Vector2(xSpeed, ySpeed))
  }

  def checkBorders(starship: Starship, maxX: Int, maxY: Int): Starship = {
    var xSpeed = starship.speed.x
    var ySpeed = starship.speed.y

    starship.position.x match {
      case x if x < starship.starshipType.size / 2 => xSpeed = 3
      case x if x > maxX - starship.starshipType.size / 2 => xSpeed = -3
      case _ =>
    }
    starship.position.y match {
      case y if y < starship.starshipType.size / 2 => ySpeed = 3
      case y if y > maxY - starship.starshipType.size / 2 => ySpeed = -3
      case _ =>
    }
    starship.copy(speed = Vector2(xSpeed, ySpeed))
  }

}
