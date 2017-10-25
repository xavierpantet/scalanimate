package scalanimate.shapes.mutable

import doodle.core.Angle

import scalanimate.Canvas
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLImageElement

case class Image(imageURL: String, override var x: Double, override var y: Double, var width: Double, var height: Double)(implicit override val canvas: Canvas) extends MutableShape {
  /**
    * Contains the actual HTML element to store the image
    */
  private lazy val image: HTMLImageElement = {
    val image = dom.document.createElement("img").asInstanceOf[HTMLImageElement]
    image.src = imageURL
    image
  }

  /**
    * Describes the rotation of the shape with respect to its width
    *
    * @return a pair containing the x and y values of the rotation
    */
  private def xRotationTransform = (height * Math.cos((angle + Angle.degrees(90)).toRadians), height * Math.sin((angle + Angle.degrees(90)).toRadians))

  /**
    * Describes the rotation of the shape with respect to its height
    *
    * @return a pair containing the x and y values of the rotation
    */
  private def yRotationTransform = (width * Math.cos(angle.toRadians), width * Math.sin(angle.toRadians))

  /**
    * Returns the top right corner of the rectangle
    *
    * @return (x, y) coordinates of the top right corner
    */
  private def bottomRightCorner = (x + xRotationTransform._1, y + xRotationTransform._2)

  /**
    * Returns the bottom left corner of the rectangle
    *
    * @return (x, y) coordinates of the bottom left corner
    */
  private def topLeftCorner = (x + yRotationTransform._1, y + yRotationTransform._2)

  /**
    * Returns the bottom right corner of the rectangle
    *
    * @return (x, y) coordinates of the bottom right corner
    */
  private def topRightCorner = (topLeftCorner._1 + xRotationTransform._1, topLeftCorner._2 + xRotationTransform._2)

  /**
    * Describes a rectangle path containing the image so that we can detect its edges
    */
  private def getPath = {
    canvas.context.beginPath
    canvas.context.moveTo(x, y)

    // Draw the bottom line
    canvas.context.lineTo(bottomRightCorner._1, bottomRightCorner._2)

    // Draw the right line
    canvas.context.lineTo(bottomRightCorner._1 + yRotationTransform._1, bottomRightCorner._2 + yRotationTransform._2)
    canvas.context.moveTo(x, y)

    // Draw the left line
    canvas.context.lineTo(topLeftCorner._1, topLeftCorner._2)

    // Draw the top line
    canvas.context.lineTo(topRightCorner._1, topRightCorner._2)
  }

  /**
    * Returns the position of the center of the shape
    *
    * @return the position of the center of the shape
    */
  override def center: (Double, Double) = (x + image.width / 2, y + image.height / 2)

  /**
    * Actually draws the shape on the canvas
    *
    * @param canvas the canvas to draw on
    */
  override def draw(implicit canvas: Canvas): Unit = {
    if (showing) {
      canvas.context.translate(x, y)
      canvas.context.rotate(angle.toRadians)
      canvas.context.drawImage(image, 0, 0, width, height)
      canvas.context.rotate(-angle.toRadians)
      canvas.context.translate(-x, -y)
    }
  }

  /**
    * Returns true iff the point (x, y) belongs to the shape
    *
    * @param x the x coordinate of the point
    * @param y the y coordinate of the point
    */
  override def contains(x: Double, y: Double): Boolean = {
    getPath
    canvas.context.isPointInPath(x, y)
  }
}

object Image{
  val Pindex = "images/pindex.png"
}