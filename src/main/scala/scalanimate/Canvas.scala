package scalanimate

import org.scalajs.dom
import org.scalajs.dom.html.{Canvas => JSCanvas}
import org.scalajs.dom.raw.{KeyboardEvent, MouseEvent}
import org.scalajs.dom.{CanvasRenderingContext2D, document}

import scala.collection.mutable.ListBuffer
import scalanimate.shapes.mutable.MutableShape

/**
  * The canvas class represents the drawing area of a web page.
  * It is uniquely identified by the provided id and has a width and a height.
  * Moreover, it uses internally a ListBuffer to store the list of the shapes to draw.
  * @param id HTML id attribute to identify the canvas in the page
  * @param width width of the drawing area
  * @param height height of the drawing area
  * @param shapes a list of shapes to by drawn into the canvas
  */
class Canvas(val id: String, val width: Int, val height: Int, val shapes: ListBuffer[MutableShape] = ListBuffer.empty) {
  /**
    * This is the actual HTML element representing the canvas
    */
  val canvas: JSCanvas = getCanvas(id, width, height)

  /**
    * And here is the drawing context (in which we will actually draw)
    */
  val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  /**
    * X-coordinate (respectively t0 the canvas) of the mouse
    */
  var mouseX: Double = 0

  /**
    * Y-coordinate (respectively to the canvas) of the mouse
    */
  var mouseY: Double = 0

  /**
    * A set containing the keyboard keys that are currently pressed
    */
  var keysDown: Set[String] = Set()

  /**
    * This clears the canvas
    */
  def clear = context.clearRect(0, 0, width, height)

  /**
    * Clears the canvas and redraw all the shapes
    */
  def update = {
    clear
    shapes.filter(_.showing).foreach(_.draw(this))
  }

  /**
    * Gets, configures and returns a canvas from its id and the given width and height
    * @param id HTML id attribute to identify the canvas in the page
    * @param width width of the drawing area
    * @param height height of the drawing area
    * @return a HTML canvas with the given id, width and height
    */
  private def getCanvas(id: String, width: Int, height: Int): JSCanvas = {
    // Get the canvas and sets its size
    val canvas = document.getElementById(id).asInstanceOf[JSCanvas]
    canvas.height = height
    canvas.width = width

    // Keeps up to date the coordinates of the mouse (relatively to the canvas)
    canvas.onmousemove = { (e: MouseEvent) =>
      mouseX = e.clientX - canvas.getBoundingClientRect().left
      mouseY = e.clientY - canvas.getBoundingClientRect().top
    }

    // Keep up to date the set of currently pressed keyboard keys
    dom.window.onkeydown = { (e: KeyboardEvent) =>
      keysDown += e.key
    }

    dom.window.onkeyup = {( e:KeyboardEvent) =>
      keysDown -= e.key
    }

    // Redirects the onMouseDown, onMouseUp, onMouseOver events to the shapes
    canvas.onmousedown = _ => shapes.filter(_.contains(mouseX, mouseY)).foreach(_.onMouseDown.apply())
    canvas.onmouseup = _ => shapes.filter(_.contains(mouseX, mouseY)).foreach(_.onMouseUp.apply())

    // Returns the canvas
    canvas
  }
}

object Canvas{
  def apply(id: String, width: Int, height: Int): Canvas = new Canvas(id, width, height)
}
