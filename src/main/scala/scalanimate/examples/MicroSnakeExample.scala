package scalanimate.examples

import org.scalajs.dom.ext.KeyValue._

import scala.concurrent.duration._
import scala.util.Random
import scalanimate.core.TimeControls._
import scalanimate.shapes.mutable.{Background, Image}

object MicroSnakeExample extends Example {
  override def run: Unit = {
    ready{
      Image(Background.Grass, 0, 0, width, height)
      val puppy = Image(Image.PuppyEmoji, width / 2, height / 2, 50, 50)
      var apple = getRandomApple

      every(20.milliseconds){_ => {
        if(canvas.keysDown.contains(ArrowUp)){
          puppy.move(0, -10)
        }
        else if(canvas.keysDown.contains(ArrowDown)){
          puppy.move(0, 10)
        }
        else if(canvas.keysDown.contains(ArrowLeft)){
          puppy.move(-10, 0)
        }
        else if(canvas.keysDown.contains(ArrowRight)){
          puppy.move(10, 0)
        }

        if(puppy.touches(apple)){
          canvas.shapes = canvas.shapes.take(2)
          apple = getRandomApple
        }
      }}
    }

  }

  private def getRandomApple = Image(Image.Apple, Random.nextInt(width-10), Random.nextInt(height-10), 50, 50)
}
