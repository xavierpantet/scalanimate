import scala.concurrent.duration._
import scalanimate.Canvas
import scalanimate.shapes.mutable.{Circle, Image}
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    val c1 = Image(Image.Girl, 200, 150, 100, 100)
    c1.turnRight(10)
    c1.onMouseDown = () => c1.hide()
    c1.onMouseUp = () => c1.show()
    canvas.update

    every(1.second){ t => {
      if(t % 2 == 0)
        c1.flipVertically
      else
        c1.flipHorizontally
    }}
  }
}