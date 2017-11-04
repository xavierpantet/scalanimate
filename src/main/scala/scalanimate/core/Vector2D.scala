package scalanimate.core

import doodle.core.Angle

import scalanimate.core.GeometryHelper.Point

/**
  * This provides a representation for vectors in a 2-dimensional vector space
  * @param x the x component of the vector
  * @param y the y component of the vector
  */
class Vector2D(val x: Double, val y: Double) {
  /**
    * Computes the norm (= the length) of the vector
    * @return the norm
    */
  def norm: Double = Math.sqrt(x*x + y*y)

  /**
    * Normalizes a vector (= make its length be 1)
    * @return normalized vector
    */
  def normalize: Vector2D = Vector2D(x/norm, y/norm)

  /**
    * Computes a normal (= perpendicular) vector
    * @return perpendicular vector
    */
  def normal: Vector2D = Vector2D(y, -x)

  /**
    * Multiplies a vector by the given scalar coefficient
    * @param l the coefficient
    * @return vector multiplied by given scalar
    */
  def multipliedBy(l: Double) = Vector2D(x*l, y*l)

  /**
    * Adds two vectors
    * @param other another vector
    * @return the addition of the two vectors
    */
  def +(other: Vector2D) = Vector2D(x+other.x, y+other.y)

  /**
    * Subtracts two vectors
    * @param other another vector
    * @return the subtraction of the two vectors
    */
  def -(other: Vector2D) = Vector2D(x-other.x, y-other.y)

  /**
    * Performs a scala product operation on two vectors
    * @param other another vector
    * @return scala product of the two vectors
    */
  def dot(other: Vector2D): Double = x*other.x + y*other.y

  /**
    * Negates a vector (ie changes its direction)
    * @param v the vector
    * @return vector with opposite direction
    */
  def unary_-(v: Vector2D): Vector2D = v multipliedBy (-1)

  /**
    * Computes the angle between two vectors
    * @param other another vector
    * @return angle between the two vectors
    */
  def angleWith(other: Vector2D): Angle = Angle.radians((this dot other)/(norm * other.norm))

  /**
    * Computes the scalar projection of two vectors
    * @param other another vector
    * @return scalar projection of two vectors
    */
  def scalarProjection(other: Vector2D): Double = norm * Math.cos(angleWith(other).toRadians)

  /**
    * Computes the projection of a vector onto another
    * @param other another vector
    * @return the projection of the two vectors
    */
  def projectedOn(other: Vector2D): Vector2D = other.normalize multipliedBy scalarProjection(other)

  override def toString: String = "(" + x + ", " + y + ")"
}

object Vector2D{
  def apply(x: Double, y: Double) = new Vector2D(x, y)
  def fromPoints(p1: Point, p2: Point): Vector2D = new Vector2D(p2._1-p1._1, p2._2-p1._2)
}