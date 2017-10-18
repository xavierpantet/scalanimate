package scalanimate.shapes.mutable

import doodle.core.{Angle, Color}
import scalanimate.Canvas
import scalanimate.core.GeometryHelper._
import scalanimate.shapes.Shape

/**
  * This is abstract class is meant to define the API relative to imperative style graphical programming.
  * It should be close to the WoofJS API and therefore relies on mutable elements.
  * Shapes are drawn into <canvas> tags using Scala.js.
  */
abstract class MutableShape(implicit val canvas: Canvas) extends Shape {
  var x: Double
  var y: Double
  var angle: Angle = Angle(0)
  var showing: Boolean = true
  var fillColor = Color.white
  var borderColor = Color.black
  var onMouseDown: () => Unit = () => {}
  var onMouseUp: () => Unit = () => {}

  canvas.shapes += this
  this.draw

  /**
    * Returns the position of the center of the shape
    * @return the position of the center of the shape
    */
  def center: Position

  /**
    * Moves the shapes of a distance d in its own direction
    *
    * @param d the distance to move the shape
    */
  def move(d: Double): Unit = {
    val radAngle = angle.toRadians
    x += d*Math.cos(radAngle)
    y += d*Math.sin(radAngle)
  }

  /**
    * Moves the shapes of distance dx along the x axis and dx along the dy axis
    *
    * @param dx the distance along the x axis
    * @param dy the distance along the y axis
    */
  def move(dx: Double, dy: Double): Unit = {
    x += dx
    y += dy
  }

  /**
    * Moves the shape to the point of given coordinates
    *
    * @param x the x coordinate of the new position
    * @param y the y coordinate of the new position
    */
  def moveTo(x: Double, y: Double): Unit = {
    this.x = x
    this.y = y
  }

  /**
    * Rotates the shape to the left of the given angle (in degrees)
    * @param angle the angle (in degrees)
    */
  def turnLeft(angle: Double): Unit = this.angle = this.angle - Angle.degrees(angle)

  /**
    * Rotates the shape to the right of the given angle (in degrees)
    * @param angle the angle (in degrees)
    */
  def turnRight(angle: Double): Unit = this.angle = this.angle + Angle.degrees(angle)

  /**
    * Rotates the shape so that it points towards the given point
    * @param x the x coordinate of the point to be pointed
    * @param y the y coordinate of the point to be pointed
    */
  def pointTowards(x: Double, y: Double): Unit = angle = Angle.radians(Math.atan2(y, x))

  /**
    * Rotates the shape so that it points towards another given shape
    * @param other the shape to be pointed
    */
  def pointTowards(other: MutableShape): Unit = pointTowards(other.center._1, other.center._2)

  /**
    * Calculates the distance between the center of two shapes
    *
    * @param other the shape we want to calculate the distance to
    */
  def distanceTo(other: MutableShape): Unit = distanceBetween((x, y), other.center)

  /**
    * Shows the shape on the screen
    */
  def show() = showing = true

  /**
    * Hide the shape from the screen
    */
  def hide() = showing = false

  /**
    * Actually draws the shape on the canvas
    * @param canvas the canvas to draw on
    */
  def draw(implicit canvas: Canvas): Unit

  /**
    * Returns true iff the point (x, y) belongs to the shape
    * @param x the x coordinate of the point
    * @param y the y coordinate of the point
    */
  def contains(x: Double, y: Double): Boolean
}