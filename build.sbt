// Enable Scala.js + Workbench plugin
enablePlugins(ScalaJSPlugin)
enablePlugins(WorkbenchPlugin)

// Project name + basic definitions
name := "scalanimate"
scalaVersion := "2.12.2"

// Dependencies to Doodle
resolvers += Resolver.bintrayRepo("underscoreio", "training")
libraryDependencies += "underscoreio" %% "doodle" % "0.8.2"
