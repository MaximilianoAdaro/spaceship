package edu.austral.spaceship.base.model

import edu.austral.spaceship.base.model.sprites.{Asteroid, Bullet, Starship, Weapon}

case class Player(name: String, controls: Controls)

case class Controls(upKey: Char, downKey: Char, leftKey: Char, rightKey: Char, shootKey: Char)

case class GameSprites(starships: List[Starship], asteroids: List[Asteroid], weapons: List[Weapon], bullets: List[Bullet])
