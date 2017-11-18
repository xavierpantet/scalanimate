package scalanimate.examples

import scala.util.Random
import scala.concurrent.duration._
import doodle.core.Color

import scalanimate.shapes.mutable.{Background, Circle, Image}
import scalanimate.core.TimeControls._

object SpaceGameExample extends Example {
  override def run: Unit = {

    // Set a background
    Image(Background.Space, 0, 0, width, height)

    // Set up rocket and a dot + number of points
    val spaceCraft = Image(Image.Rocket, width/2, height, 80, 50)
    var dot = getRandomCircle
    var points = 0

    every(20.milliseconds){_ => {

      // Update position of the rocket + its direction
      spaceCraft.x = canvas.mouseX
      spaceCraft.y = canvas.mouseY
      spaceCraft.pointTowards(dot)

      // When the rocket touches the dot, increase the number of points + generate new point
      if(spaceCraft.touches(dot)){
        points = points + 1
        println("You have " + points + " points!")
        canvas.shapes = canvas.shapes.take(2)
        dot = getRandomCircle
      }
    }}
  }

  private def getRandomCircle = {
    val c = Circle(Random.nextInt(width), Random.nextInt(height), 10)
    c.fillColor = Color.yellow
    c
  }
}
