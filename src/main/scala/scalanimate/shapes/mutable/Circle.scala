package scalanimate.shapes.mutable

import scalanimate.core.GeometryHelper
import scalanimate.Canvas
import scalanimate.core.GeometryHelper.Point

case class Circle(override var x: Double, override var y: Double, var r: Double)(implicit override val canvas: Canvas) extends MutableShape {

  /**
    * Returns the position of the center of the shape
    *
    * @return the position of the center of the shape
    */
  override def center: Point = (x, y)

  /**
    * Returns true iff the point (x, y) belongs to the shape
    *
    * @param x the x coordinate of the point
    * @param y the y coordinate of the point
    */
  override def contains(x: Double, y: Double): Boolean = GeometryHelper.distanceBetween((this.x, this.y), (x, y)) <= r

  /**
    * Actually draws the shape on the canvas
    * @param canvas the canvas to draw on
    */
  override def draw(implicit canvas: Canvas): Unit = {
    canvas.context.beginPath()
    canvas.context.arc(x, y, r, 0, 2 * Math.PI)
    canvas.context.fillStyle = fillColor.toCanvas
    canvas.context.fill()
    canvas.context.strokeStyle = borderColor.toCanvas
    canvas.context.stroke()
  }

  /**
    * Returns a list containing a normal vector for each edge of the shape
    * @return a list of normal vectors
    */
  override def getNormalEdgesVectors = Nil

  /**
    * Returns a list containing vectors from the center of the shape to each one of its corners
    * @return a list of center-corner vectors for every corner
    */
  override def getVectorsFromCenterToEveryCorner = Nil
}