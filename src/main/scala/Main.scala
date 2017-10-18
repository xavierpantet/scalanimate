import doodle.core.{Angle, Color}

import scala.concurrent.duration._
import scalanimate.Canvas
import scalanimate.shapes.mutable.{Circle, Rectangle}
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    // Basic definitions + create the canvas
    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    ////////////////////////////////////////////////////////////
    // Optional here. This simply calls canvas.update so that //
    // updated shapes are drawn again at first rendering.     //
    ////////////////////////////////////////////////////////////
    ready {

      // Create a few shapes
      val c1 = Rectangle(width / 2, height / 2, 100, 200)
      c1.fillColor = Color.red
      // Add actions on clicks on c1
      c1.onMouseDown = () => c1.hide()
      c1.onMouseUp = () => c1.show()

      val c2 = Circle(width / 2, height / 2, 50)
      c2.fillColor = Color.yellow

      val c3 = Circle(400, 0, 10)
      c3.fillColor = Color.orange

      // Every 20ms we update the canvas to give an impression of movement
      every(20.milliseconds) {
        t => {
          // Make c1 point towards c2 at all time
          c1.pointTowards(c2)

          // Put c3 in orbit
          c3.moveTo(400*Math.cos(Angle(t.toDouble/50).toRadians), 400*Math.sin(Angle(t.toDouble/50).toRadians))

          // Keep c1 and c2 under the mouse (with coef 1.5 for c2)
          if (!canvas.keysDown.contains("Shift")) {
            c1.x = canvas.mouseX
            c1.y = canvas.mouseY
            c2.moveTo(1.5*canvas.mouseX, 1.5*canvas.mouseY)
          }
          else {
            c1.x = width / 2
            c1.y = height / 2
          }
        }
      }
    }
  }
}