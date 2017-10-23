import doodle.core.{Angle, Color}

import scala.concurrent.duration._
import scalanimate.Canvas
import scalanimate.shapes.mutable.{Circle, Oval, Polygon, Rectangle}
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)
    val c1 = Oval(width / 2, height / 2, 200, 100)
    c1.fillColor = Color.yellow

    every(20.milliseconds) { _ => {
      c1.turnRight(10)
      c1.onMouseDown = () => c1.hide()
      c1.onMouseUp = () => c1.show()
    }}
  }
}