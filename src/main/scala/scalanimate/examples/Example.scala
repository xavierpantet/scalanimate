package scalanimate.examples

import scalanimate.Canvas

trait Example {
  val width = 600
  val height = 600

  implicit val canvas = Canvas("canvas", width, height)
  def run: Unit
}
