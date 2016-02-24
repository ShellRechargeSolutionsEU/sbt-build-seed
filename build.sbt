import Defaults._

sbtPlugin := true
enablePlugins(OssLibPlugin)

organization := "com.thenewmotion"
name := "sbt-build-seed"

scalaVersion := tnm.ScalaVersion.prev
releaseCrossBuild := false


libraryDependencies ++=
  Seq(
    "org.scala-sbt" % "sbt" % "0.13.11") ++
  Seq(
    "com.github.gseitz" % "sbt-release" % "1.0.2",
    "no.arktekk.sbt" % "aether-deploy" % "0.16"
  ).map(
    sbtPluginExtra(_, "0.13", "2.10")
  )

