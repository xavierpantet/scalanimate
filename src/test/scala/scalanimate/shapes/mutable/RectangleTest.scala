package scalanimate.shapes.mutable

import scalanimate.core.Vector2D

class RectangleTest extends MutableShapeTest {
  val aligned = Rectangle(100, 100, 50, 50)
  val rotated = Rectangle(100, 100, 50, 50)
  rotated.turnLeft(30)

  describe("A rectangle"){
    it("Should return the correct edges vector when aligned"){
      val testedVectors = aligned.getCornerVectors

      assert(testedVectors.distinct.size == 5)

      val expectedVectors = List(Vector2D(100, 100), Vector2D(150, 100), Vector2D(100, 50), Vector2D(150, 50))
      assert(testedVectors.forall(v => expectedVectors.exists(_ == v)))
    }
  }

  it("Should return the correct axis when aligned"){
    val testedAxis = aligned.getNormalEdgesVectors

    assert(testedAxis.distinct.size == 4)

    val expectedAxis = List(Vector2D(0, 1), Vector2D(1, 0), Vector2D(0, -1), Vector2D(-1, 0))
    assert(testedAxis.forall(v => expectedAxis.exists(_ == v)))
  }

  it("Should return the correct axis when rotated"){
    val testedAxis = rotated.getNormalEdgesVectors

    assert(testedAxis.distinct.size == 4)

    val expectedAcxis = List(Vector2D(0.5, Math.sqrt(3)/2), Vector2D(0.5, Math.sqrt(3)/2).normal, Vector2D(-0.5, Math.sqrt(3)/2), Vector2D(-0.5, Math.sqrt(3)/2).normal)
    assert(testedAxis.forall(v => testedAxis.exists(_ == v)))
  }
}
