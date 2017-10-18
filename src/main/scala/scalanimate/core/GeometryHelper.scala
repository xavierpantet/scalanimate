package scalanimate.core

/**
  * A simple helper object for geometric computations
  */
object GeometryHelper {
  type Position = (Double, Double)

  /**
    * Computes the euclidean distance between two points in a 2D plan
    * @param p1 the first point
    * @param p2 the second point
    * @return the euclidean distance between p1 and p2
    */
  def distanceBetween(p1: Position, p2: Position) = {
    val (x1, y1) = p1
    val (x2, y2) = p2
    Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))
  }
}
