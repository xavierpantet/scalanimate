package scalanimate.shapes.mutable

import org.scalajs.dom.document
import org.scalajs.dom.raw.HTMLCanvasElement
import org.scalatest.FunSpec

import scalanimate.Canvas

class RectangleTest extends FunSpec {
  implicit val c = Canvas(document.createElement("canvas").asInstanceOf[HTMLCanvasElement], 100, 100)
  val aligned = Rectangle(100, 100, 50, 50)
  val rotated = Rectangle(100, 100, 50, 50)
  rotated.turnLeft(30)

  describe("Rectangles"){
    it("Should return the correct edges vector"){
      assert(aligned.getVectorsFromCenterToEveryCorner.size == 4)
    }
  }
}
