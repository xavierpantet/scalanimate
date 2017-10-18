package scalanimate.core

class Point(private val x: Double, private val y: Double) {
  def move(dx: Double, dy: Double): Point = Point(x+dx, y+dy)
  def distanceTo(other: Point): Double = Math.sqrt((x-other.x)*(x-other.x) + (y-other.y)*(y-other.y))
}

object Point {
  def apply(x: Double, y: Double) = new Point(x, y)
}