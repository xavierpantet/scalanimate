package scalanimate.shapes.immutable

import doodle.core.Image.circle

case class Circle(r: Double) extends ImmutableShape {
  override val image = circle(r)
}
