package scalanimate.shapes.mutable

import scalanimate.Canvas
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLImageElement

case class Image(imageURL: String, override var x: Double, override var y: Double)(implicit override val canvas: Canvas) extends MutableShape {
  /**
    * Contains the actual HTML element to store the image
    */
  private lazy val image: HTMLImageElement = {
    val image = dom.document.createElement("img").asInstanceOf[HTMLImageElement]
    image.src = imageURL
    image
  }

  /**
    * Returns the position of the center of the shape
    *
    * @return the position of the center of the shape
    */
  override def center: (Double, Double) = (x + image.width/2, y + image.height /2)

  /**
    * Actually draws the shape on the canvas
    *
    * @param canvas the canvas to draw on
    */
  override def draw(implicit canvas: Canvas): Unit = {
    if(showing)
      canvas.context.drawImage(image, x, y)
  }

  /**
    * Returns true iff the point (x, y) belongs to the shape
    *
    * @param x the x coordinate of the point
    * @param y the y coordinate of the point
    */
  override def contains(x: Double, y: Double): Boolean = x > this.x && x < this.x + image.width && y > this.y && y < this.y + image.height
}

object Image{
  val Pindex = "images/pindex.png"
}