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

    val c = Circle(width/2, height/2, 100)
    val p = Circle(width/2, height/2, 50)

    every(20.milliseconds){ _ => {
      c.x = canvas.mouseX
      c.y = canvas.mouseY
      if(c.touches(p)){
        c.fillColor = Color.red
      }
      else if(c.touchesEdge){
        c.fillColor = Color.blue
      }
      else{
        c.fillColor = Color.green
      }
    }}
  }
}