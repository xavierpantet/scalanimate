import doodle.core.{Angle, Color}

import scala.concurrent.duration._
import scalanimate.Canvas
import scalanimate.shapes.mutable.{Circle, Rectangle}
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    val c1 = Rectangle(width / 2, height / 2, 100, 200)

    c1.turnLeft(20)
    every(20.milliseconds) { _ => {
      c1.fillColor = Color.red
      c1.onMouseDown = () => c1.hide()
      c1.onMouseUp = () => c1.show()
    }}
  }
}