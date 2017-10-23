import doodle.core.{Angle, Color}

import scala.concurrent.duration._
import scalanimate.Canvas
import scalanimate.shapes.mutable.{Circle, Polygon, Rectangle}
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    ready {
      val c1 = Polygon(width / 2, height / 2, 200, 10)
      c1.turnRight(10)

      c1.fillColor = Color.yellow
      c1.onMouseDown = () => c1.hide()
      c1.onMouseUp = () => c1.show()
    }
  }
}