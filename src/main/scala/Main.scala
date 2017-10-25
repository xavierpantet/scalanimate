import doodle.core.Angle

import scala.concurrent.duration._
import scala.util.Random
import scalanimate.Canvas
import scalanimate.shapes.mutable.Circle
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    every(20.milliseconds){ _ => {
      val c = Circle(width/2, height/2, 10)
      c.angle = Angle.degrees(Random.nextInt(360))
      canvas.shapes.foreach(_.move(5))
    }}

  }
}