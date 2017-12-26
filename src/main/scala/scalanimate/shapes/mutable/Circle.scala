package scalanimate.shapes.mutable

import scalanimate.core.Canvas

object Circle{
  def apply(x: Double, y: Double, r: Double)(implicit canvas: Canvas) = Oval(x, y, r, r)
}