import doodle.core.{Angle, Color}

import scala.concurrent.duration._
import scalanimate.Canvas
import scalanimate.shapes.mutable.Circle
import scalanimate.core.TimeControls._


object Main {
  def main(args: Array[String]): Unit = {

    val width = 600
    val height = 600
    implicit val canvas = Canvas("canvas", width, height)

    val p = Circle(width/2, height/2, 100)
    val c = Circle(width/2, height/2, 50)

    //p.turnLeft(37)

    every(20.milliseconds){ _ => {
      c.x = canvas.mouseX
      c.y = canvas.mouseY
      if(c.touches(p)){
        c.fillColor = Color.red
      }
      /*ele if(c.touchesEdge){
        c.fillColor = Color.blue
      }*/
      else{
        c.fillColor = Color.green
      }
    }}
  }
}