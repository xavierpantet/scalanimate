package scalanimate.shapes.mutable

import scalanimate.Canvas
import scalanimate.core.GeometryHelper.Point
import scalanimate.core.Vector2D

case class Polygon(override var x: Double, override var y: Double, var r: Double, var n: Int)(implicit override val canvas: Canvas) extends MutableShape {
  /**
    * Defines the path to draw the polygon onto the canvas
    * without actually printing it
    */
  private def getPath = {
    canvas.context.beginPath
    (0 to n).map(_*centerAngle + angle.toRadians).foreach(angle => canvas.context.lineTo(x + r*Math.cos(angle), y + r*Math.sin(angle)))
  }

  /**
    * Returns the angle (in radians) in the center of the polygon, depending on the number of sides that we want
    * @return the angle (in radians) in the center of the polygon
    */
  private def centerAngle: Double = 2*Math.PI / n

  /**
    * Returns the position of the center of the shape
    *
    * @return the position of the center of the shape
    */
  override def center: Point = (x, y)

  /**
    * Actually draws the shape on the canvas
    *
    * @param canvas the canvas to draw on
    */
  override def draw(implicit canvas: Canvas): Unit = {
    getPath
    canvas.context.fillStyle = fillColor.toCanvas
    canvas.context.fill
    canvas.context.strokeStyle = borderColor.toCanvas
    canvas.context.stroke
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

  /**
    * Returns a list containing a normal vector for each edge of the shape
    * @return a list of normal vectors
    */
  override def getNormalEdgesVectors = {
    val angles = (1 to n).map(_*centerAngle + angle.toRadians).map(angle => (x + r*Math.cos(angle), y + r*Math.sin(angle))).toList
    (angles zip rotateLeft(angles, 1)).map{ case (a1, a2) => Vector2D.fromPoints(a1, a2).normal}
  }

  /**
    * Rotates a list to the left by the given number of iterations
    * @param l the list to be rotated
    * @param i the number of iterations
    * @return the list rotated by i iteration to the left
    */
  private def rotateLeft[A](l: List[A], i: Int): List[A] = {
    val size = l.size
    l.drop(i % size) ++ l.take(i % size)
  }

  /**
    * Returns a list containing vectors from the center of the shape to each one of its corners
    * @return a list of center-corner vectors for every corner
    */
  override def getVectorsFromCenterToEveryCorner = (0 to n).map(_*centerAngle + angle.toRadians).map(angle => Vector2D.fromPoints(center, (x + r*Math.cos(angle), y + r*Math.sin(angle)))).toList
}
