package scalanimate.examples

import scala.util.Random
import scala.concurrent.duration._
import doodle.core.Color

import scalanimate.shapes.mutable.{Background, Circle, Image}
import scalanimate.core.TimeControls._

object SpaceGameExample extends Example {
  override def run: Unit = {
    Image(Background.Space, 0, 0, width, height)
    val spaceCraft = Image(Image.Rocket, width/2, height, 80, 50)
    var dot = getRandomCircle
    var points = 0

    every(20.milliseconds){_ => {
      spaceCraft.x = canvas.mouseX
      spaceCraft.y = canvas.mouseY
      spaceCraft.pointTowards(dot)

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
