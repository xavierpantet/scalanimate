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

    val p = Polygon(width/2, height/2, 50, 4)
    val c = Rectangle(width/2, height/2, 10, 10 )
    /*c.hide()
    p.turnLeft(30)
    for {
      x <- 0 to width by 10
      y <- 0 to height by 10
    } yield {
      c.moveTo(x, y)
      if(c.touches(p)){
        Circle(x, y, 2)
      }
    }*/

    //p.getVectorsFromCenterToEveryCorner.foreach{p => Circle(p.x, p.y, 5)}

    //Rectangle(width/2 - 3, height/2 + 2, 10, 10)
    //p.getNormalEdgesVectors.foreach(p => Rectangle(width/2 -3 +p.x*50, height/2 + 2 + p.y*50, 10, 10))
    canvas.update

    every(20.milliseconds){ _ => {
      c.x = canvas.mouseX
      c.y = canvas.mouseY
      if(p.touches(c)){
        Circle(canvas.mouseX, canvas.mouseY, 5)
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