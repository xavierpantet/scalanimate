// Enable Scala.js + Workbench plugin
enablePlugins(ScalaJSPlugin)
scalaJSUseMainModuleInitializer := true
enablePlugins(WorkbenchPlugin)

// Project name + basic definitions
name := "scalanimate"
scalaVersion := "2.12.2"

// Dependencies to Scala.js dom
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.2"

// Dependencies to Doodle
resolvers += Resolver.bintrayRepo("underscoreio", "training")
libraryDependencies += "underscoreio" %%% "doodle" % "0.8.2"

// Bintray config
bintrayRepository := "scalanimate"
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
bintrayPackageLabels := Seq("scalanimate", "scala", "animation")
