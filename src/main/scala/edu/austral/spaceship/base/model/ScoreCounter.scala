package edu.austral.spaceship.base.model

import edu.austral.spaceship.base.model.sprites.{Asteroid, Bullet}

object ScoreCounter {

  var scores: Map[String, Int] = Map()

  def init(players: List[Player]): Unit = players.foreach(player => scores = scores.updated(player.name, 0))

  def getScores: List[String] = scores.toList.map { case (name: String, value: Int) => name + ": " + value }


  def onBulletCollisionWithAsteroid(bullet: Bullet, asteroid: Asteroid): Unit = {
    val name = bullet.weapon.starship.player.name
    val points = asteroid.asteroidType.points
    scores = scores.updatedWith(name)(old => old.map(value => {
      value + points
    }) orElse Some(points))
  }

  def onBulletCollisionWithStarship(bullet: Bullet): Unit = {
    val name = bullet.weapon.starship.player.name
    scores = scores.updatedWith(name)(old => old.map(value => value + 500))
  }

  def reset(name: String): Unit = {
    scores = scores.updated(name, 0)
  }

}
