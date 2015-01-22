import Defaults._

sbtPlugin := true

organization := "com.thenewmotion"
name := "sbt-build-seed"
version := "0.1.1-SNAPSHOT"

scalaVersion in Global := "2.10.4"

publishTo := Some {
  val destination = (if (isSnapshot.value) "snapshots" else "releases")+"-public"
  "TNM" at s"http://nexus.thenewmotion.com/content/repositories/$destination"
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

libraryDependencies ++=
  Seq(
    "com.github.gseitz" % "sbt-release" % "0.8.5",
    "no.arktekk.sbt" % "aether-deploy" % "0.11"
  ).map(
    sbtPluginExtra(_, "0.13", "2.10")
  )
