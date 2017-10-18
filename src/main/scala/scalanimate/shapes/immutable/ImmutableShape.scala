package scalanimate.shapes.immutable
import scalanimate.shapes.Shape
import doodle.core.Image

abstract class ImmutableShape extends Shape {
  val image: Image
}
