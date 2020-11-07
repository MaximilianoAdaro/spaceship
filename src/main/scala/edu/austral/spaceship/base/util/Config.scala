package edu.austral.spaceship.base.util

import java.util.Properties

import scala.io.Source

object Config {

  private val properties: Properties = new Properties()

  def init(fileName: String): Unit = {
    val url = getClass.getResource(fileName)
    if (url != null) {
      val source = Source.fromURL(url)
      properties.load(source.bufferedReader())
    } else {
      throw new RuntimeException("Could not read file")
    }
  }

  def getIntegerProperty(key: String): Int = properties.getProperty(key).toInt

  def getCharProperty(key: String): Char = properties.getProperty(key).toCharArray.head

  def getProperty(key: String): String = properties.getProperty(key)

}
