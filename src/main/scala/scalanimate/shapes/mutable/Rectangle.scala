package scalanimate.shapes.mutable

import doodle.core.Angle

import scalanimate.Canvas

case class Rectangle(override var x: Double, override var y: Double, var width: Double, var height: Double)(implicit override val canvas: Canvas) extends MutableShape {

  /**
    * Returns the position of the center of the shape
    * @return the position of the center of the shape
    */
  override def center: (Double, Double) = (x + width/2, y + height/2)

  /**
    * Actually draws the shape on the canvas
    * @param canvas the canvas to draw on
    */
  override def draw(implicit canvas: Canvas): Unit = {
    val xRotationTransform = (height*Math.cos(angle.toRadians), height*Math.sin(angle.toRadians))
    val yRotationTransform = (width*Math.cos((angle-Angle.degrees(90)).toRadians), width*Math.sin((angle-Angle.degrees(90)).toRadians))
    val topRightCorner = (x + xRotationTransform._1, y + xRotationTransform._2)
    val bottomLeftCorner = (x + yRotationTransform._1, y + yRotationTransform._2)
    val bottomRightCorner = (bottomLeftCorner._1 + xRotationTransform._1, bottomLeftCorner._2 + xRotationTransform._2)

    canvas.context.beginPath
    canvas.context.moveTo(x, y)
    canvas.context.lineTo(topRightCorner._1, topRightCorner._2)
    canvas.context.lineTo(topRightCorner._1 + yRotationTransform._1, topRightCorner._2 + yRotationTransform._2)
    canvas.context.moveTo(x, y)
    canvas.context.lineTo(bottomLeftCorner._1, bottomLeftCorner._2)
    canvas.context.lineTo(bottomRightCorner._1, bottomRightCorner._2)
    canvas.context.fillStyle = fillColor.toCanvas
    canvas.context.fill
    canvas.context.strokeStyle = borderColor.toCanvas
    canvas.context.stroke
  }

  /**
    * Returns true iff the point (x, y) belongs to the shape
    * @param x the x coordinate of the point
    * @param y the y coordinate of the point
    */
  override def contains(x: Double, y: Double): Boolean = (x >= this.x && x <= this.x + width) && (y >= this.y && y <= this.y + height)

}

object Rectangle {
  def apply(x: Double, y: Double, width: Double, height: Double)(implicit canvas: Canvas) = new Rectangle(x, y, width, height)
}