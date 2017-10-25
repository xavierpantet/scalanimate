import doodle.core.Color

import scalanimate.Canvas
import scalanimate.shapes.mutable.{Circle, Image}
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    ready {
      val c1 = Image(Image.Girl, 200, 150, 100, 50)
      val c2 = Circle(200, 150, 10)
      c1.onMouseDown = () => c1.hide()
      c1.onMouseUp = () => c1.show()
      c1.turnRight(20)
      c2.fillColor = Color.yellow
    }
  }
}