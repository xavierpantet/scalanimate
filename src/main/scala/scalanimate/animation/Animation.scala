package scalanimate.animation

import scalanimate.shapes.Shape

trait Animation {
  def next: Animation
  def shape: Shape
}
