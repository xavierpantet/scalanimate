package scalanimate.shapes.mutable

import scalanimate.core.Vector2D

class PolygonTest extends MutableShapeTest {
  val square = Polygon(100, 100, 10, 4)
  val rotatedSquare = Polygon(100, 100, 10, 5)
  rotatedSquare.turnLeft(30)

  describe("A polygon"){
    it("Should return the correct edges vector when aligned"){
      val testedVectors = square.getCornerVectors

      assert(testedVectors.distinct.size == 4)

      val expectedVectors = List(Vector2D(110, 100), Vector2D(100, 90), Vector2D(90, 100), Vector2D(110, 110))
      assert(testedVectors.forall(expectedVectors.contains(_)))
    }
  }

  /*it("Should return the correct axis when aligned"){
    val testedAxis = square.getNormalEdgesVectors

    assert(testedAxis.distinct.size == 4)

    val expectedAxis = List(Vector2D(0, 1), Vector2D(1, 0), Vector2D(0, -1), Vector2D(-1, 0))
    assert(testedAxis.forall(v => expectedAxis.exists(_ == v)))
  }

  it("Should return the correct axis when rotated"){
    val testedAxis = rotatedSquare.getNormalEdgesVectors

    assert(testedAxis.distinct.size == 4)

    val expectedAcxis = List(Vector2D(0.5, Math.sqrt(3)/2), Vector2D(0.5, Math.sqrt(3)/2).normal, Vector2D(-0.5, Math.sqrt(3)/2), Vector2D(-0.5, Math.sqrt(3)/2).normal)
    assert(testedAxis.forall(v => testedAxis.exists(_ == v)))
  }*/
}
