package scalanimate.examples

import scala.util.Random
import scala.concurrent.duration._

import scalanimate.core.TimeControls.every
import scalanimate.shapes.mutable.Circle

/**
  * This is a black and white Scalanimate version of the following animation:
  * http://woofjs.com/
  */
object WoofJSExample extends Example {
  override def run: Unit = {
    every(20.milliseconds){_ => {
      val c = Circle(width/2, height/2, 10)
      c.turnLeft(Random.nextInt(360))
      canvas.shapes.foreach(_.move(5))
      canvas.shapes = canvas.shapes.filter(!_.touchesEdge)
    }}
  }
}