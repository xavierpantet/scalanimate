# Scalanimate

An educational tool intended for people who want to discover Scala in an interactive way!

## Example

~~~ scala
// Create the canvas
val width = 600
val height = 600
implicit val canvas = Canvas("canvas", width, height)

// Create a few shapes
val c1 = Rectangle(width / 2, height / 2, 100, 200)
c1.fillColor = Color.red
c1.onMouseDown = () => c1.hide()
c1.onMouseUp = () => c1.show()

val c2 = Circle(width / 2, height / 2, 50)
c2.fillColor = Color.yellow

val c3 = Circle(400, 0, 10)
c3.fillColor = Color.orange

// Update canvas every 20ms. This gives an impression of movement.
every(20.milliseconds) { t => {
  c1.pointTowards(c2)
  c3.moveTo(400*Math.cos(Angle(t.toDouble/50).toRadians), 400*Math.sin(Angle(t.toDouble/50).toRadians))

  if (!canvas.keysDown.contains("Shift")) {
    c1.x = canvas.mouseX
    c1.y = canvas.mouseY
    c2.moveTo(1.5*canvas.mouseX, 1.5*canvas.mouseY)
  }
  else {
    c1.x = width / 2
    c1.y = height / 2
  }
}
}
~~~

## Running
~~~ shell
sbt
fastOptJS
~~~

## Testing
~~~ shell
npm i
sbt test
~~~