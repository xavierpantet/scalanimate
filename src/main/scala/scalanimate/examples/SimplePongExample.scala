package scalanimate.examples

import doodle.core.Color

import scala.concurrent.duration._
import scala.util.Random
import scalanimate.shapes.mutable.{Background, Circle, Image, Rectangle}
import scalanimate.core.TimeControls._

import scala.scalajs.js.Dynamic.global

object SimplePongExample extends Example {
  override def run: Unit = {
    // Background
    Image(Background.DarkSpace, 0, 0, width, height)

    // Define the bar
    val bar = Rectangle(width/2 - 50, height, 10, 100)

    // Define the ball + properties
    val ball = Circle(width/2, height-20, 10)
    ball.fillColor = Color.yellow
    var (dx, dy) = (1 + Random.nextInt(9), -(1 + Random.nextInt(9)))

    // Game state
    var gamePaused = true

    // GAME
    every(20.milliseconds){_ => {
      if(!gamePaused) {
        manageEdges
        if (canvas.keysDown.contains("ArrowLeft"))
          bar.move(-10, 0)
        if (canvas.keysDown.contains("ArrowRight"))
          bar.move(10, 0)
      }
      if (canvas.keysDown.contains(" "))
        gamePaused = !gamePaused
    }}

    /**
      * Private method that moves the ball depending on its position
      */
    def manageEdges: Unit = {
      if(ball.touchesEdge){
        if(ball.y <= 5 || ball.y >= height-5 || ball.touches(bar))
          dy = -dy
        if(ball.x <= 5 || ball.x >= width-5)
          dx = -dx
        if(!ball.touches(bar) && ball.y >= height-5)
          global.alert("Game Over!")
      }
      ball.move(dx, dy)
    }
  }
}
