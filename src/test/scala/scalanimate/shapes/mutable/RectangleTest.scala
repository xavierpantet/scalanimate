package scalanimate.shapes.mutable

import org.scalatest.FunSpec

import scalanimate.Canvas

class RectangleTest extends FunSpec {
  implicit val c = Canvas("test", 100, 100)
  val aligned = Rectangle(100, 100, 50, 50)
  val rotated = Rectangle(100, 100, 50, 50)
  rotated.turnLeft(30)

  describe("Rectangles"){
    it("Should return the correct edges vector"){
      assert(aligned.getVectorsFromCenterToEveryCorner.size == 4)
    }
  }
}
