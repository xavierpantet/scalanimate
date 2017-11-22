package scalanimate.examples

import doodle.core.Color

import scala.concurrent.duration._
import scala.util.Random
import scalanimate.shapes.mutable.{Background, Circle, Image, Rectangle}
import scalanimate.core.TimeControls._

import scala.scalajs.js.Dynamic.global

object SimplePongExample extends Example {
  override def run: Unit = {
    Image(Background.DarkSpace, 0, 0, width, height)
    val bar = Rectangle(width/2 - 50, height, 10, 100)
    val ball = Circle(width/2, height-20, 10)
    var dx = 1 + Random.nextInt(9)
    var dy = -(1 + Random.nextInt(9))
    ball.fillColor = Color.yellow
    var gamePaused = true

    every(20.milliseconds){_ => {
      manageEdges
      if(!gamePaused && canvas.keysDown.contains("ArrowLeft"))
        bar.move(-10, 0)
      if(!gamePaused && canvas.keysDown.contains("ArrowRight"))
        bar.move(10, 0)
      if(canvas.keysDown.contains(" "))
        gamePaused = !gamePaused
    }}

    def manageEdges: Unit = {
      if(!gamePaused && ball.touchesEdge && !ball.touches(bar)){
        if(ball.y <= 5 || ball.y >= height-5)
          dy = -dy
        if(ball.x <= 5 || ball.x >= width-5)
          dx = -dx
        if(ball.y >= height-5)
          global.alert("Game Over!")
      }
      if(ball.touches(bar))
        dy = -dy

      if(!gamePaused)
        ball.move(dx, dy)
    }
  }
}
