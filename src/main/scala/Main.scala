import doodle.core.{Angle, Color}

import scala.concurrent.duration._
import scala.util.Random
import scalanimate.Canvas
import scalanimate.shapes.mutable.{Circle, Polygon, Rectangle}
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    val p = Polygon(width/2, height/2, 100, 7)
    val c = Circle(width/2, height/2, 10)

    every(20.milliseconds){ _ => {
      c.x = canvas.mouseX
      c.y = canvas.mouseY
      if(p.touches(c)){
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