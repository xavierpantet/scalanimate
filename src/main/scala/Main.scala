import doodle.core.Color

import scala.concurrent.duration._
import scalanimate.Canvas
import scalanimate.shapes.mutable.{Circle, Image}
import scalanimate.shapes.mutable.Image._
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    val c1 = Image(Pindex, width / 2, height / 2)
    val c2 = Circle(width / 2, height / 2, 10)
    c2.fillColor = Color.yellow

    every(20.milliseconds) { _ => {
      c1.onMouseDown = () => c1.hide()
      c1.onMouseUp = () => c1.show()
    }}
  }
}