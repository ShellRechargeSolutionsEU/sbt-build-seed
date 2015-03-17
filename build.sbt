import Defaults._

sbtPlugin := true

organization := "com.thenewmotion"
name := "sbt-build-seed"
version := "0.5.0"

scalaVersion in Global := "2.10.4"

publishTo := Some {
  val destination = (if (isSnapshot.value) "snapshots" else "releases")+"-public"
  "TNM" at s"http://nexus.thenewmotion.com/content/repositories/$destination"
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

libraryDependencies ++=
  Seq(
    "org.scala-sbt" % "sbt" % "0.13.7") ++
  Seq(
    "com.github.gseitz" % "sbt-release" % "0.8.5",
    "no.arktekk.sbt" % "aether-deploy" % "0.11"
  ).map(
    sbtPluginExtra(_, "0.13", "2.10")
  )
