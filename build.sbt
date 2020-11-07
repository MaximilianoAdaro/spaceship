name := "spaceship"

version := "0.1"

scalaVersion := "2.13.3"

//mainClass in (Compile, run) := Some("edu.austral.starship.base.Main")
mainClass in (Compile, run) := Some("edu.austral.starship.scala.base.Main")

// https://mvnrepository.com/artifact/org.processing/core
libraryDependencies += "org.processing" % "core" % "3.3.7"

// https://mvnrepository.com/artifact/org.scala-lang/scala-library
//libraryDependencies += "org.scala-lang" % "scala-library" % "2.13.3"

//libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0"
