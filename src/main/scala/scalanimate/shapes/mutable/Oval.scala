package scalanimate.shapes.mutable
import scalanimate.Canvas
import scalanimate.core.GeometryHelper.Point

case class Oval(override var x: Double, override var y: Double, var width: Double, var height: Double)(implicit override val canvas: Canvas) extends MutableShape {
  /**
    * Defines the path to draw the oval onto the canvas
    * without actually printing it
    */
  private def getPath = {
    canvas.context.beginPath
    val steps = 50
    val centerAngle = 2*Math.PI / steps

    // Found here: https://math.stackexchange.com/questions/426150/what-is-the-general-equation-of-the-ellipse-that-is-not-in-the-origin-and-rotate/434482
    (0 to steps).map(_*centerAngle).foreach(t => canvas.context.lineTo(x + width*Math.cos(t)*Math.cos(angle.toRadians) - height*Math.sin(t)*Math.sin(angle.toRadians), y + width*Math.cos(t)*Math.sin(angle.toRadians) + height*Math.sin(t)*Math.cos(angle.toRadians)))
  }

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
  override def getNormalEdgesVectors = Nil

  /**
    * Returns a list containing vectors from the center of the shape to each one of its corners
    * @return a list of center-corner vectors for every corner
    */
  override def getVectorsFromCenterToEveryCorner = Nil
}
