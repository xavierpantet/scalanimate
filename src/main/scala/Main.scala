import doodle.core.Angle

import scala.concurrent.duration._
import scala.util.Random
import scalanimate.Canvas
import scalanimate.shapes.mutable.{Rectangle, Polygon, Circle}
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    /*every(20.milliseconds){ _ => {
      val c = Rectangle(width/2, height/2, 10, 10)
      c.angle = Angle.degrees(Random.nextInt(360))
      canvas.shapes.foreach(_.move(5))
      canvas.shapes.foreach(_.getNormalEdgesVectors.foreach(v => canvas.context.lineTo(v.x, v.y)))
    }}*/
    ready{
      val r = Polygon(width / 2, height / 2, 100, 5)
      r.getNormalEdgesVectors.map(v => Circle(width/2 + v.x, height/2 + v.y, 10))
    }
  }
}