package scalanimate.shapes.mutable

import org.scalajs.dom.document
import org.scalajs.dom.raw.HTMLCanvasElement
import org.scalatest.FunSpec

import scalanimate.core.Canvas

class MutableShapeTest extends FunSpec {
  protected implicit val c = Canvas(document.createElement("canvas").asInstanceOf[HTMLCanvasElement], 100, 100)
}
