package edu.austral.spaceship.base.view

import java.awt.Shape

import processing.core.PImage

case class Drawable(image: PImage, x: Float, y: Float, dir: Float, speed: Double, shape: Shape)
