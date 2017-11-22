package scalanimate.examples

import scala.math._
import scala.concurrent.duration._

import scalanimate.core.TimeControls._

object HandsOnScalaJSExample extends Example {
  override def run: Unit = {
    type Graph = (String, Double => Double)
    val graphs = Seq[Graph](
      ("red", sin),
      ("green", x => abs(x % 4 - 2) - 1),
      ("blue", x => sin(x/12) * sin(x))
    ).zipWithIndex

    everyIncr(20.milliseconds){ t => {
      val x = t.toDouble % width
      if(x == 0){canvas.clear}
      for (((color, f), i) <- graphs) {
        val offset = height / 3 * (i + 0.5)
        val y = f(x / width * 75) * height / 30
        canvas.context.fillStyle = color
        canvas.context.fillRect(x, y + offset, 3, 3)
      }
    }}
  }
}
