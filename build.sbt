import Defaults._

sbtPlugin := true
enablePlugins(LibPlugin)

organization := "com.thenewmotion"
name := "sbt-build-seed"

scalaVersion := tnm.ScalaVersion.prev
releaseCrossBuild := false

tnm.Repo.publishSettings(tnm.Repo.Public)

libraryDependencies ++=
  Seq(
    "org.scala-sbt" % "sbt" % "0.13.9") ++
  Seq(
    "com.github.gseitz" % "sbt-release" % "1.0.0",
    "no.arktekk.sbt" % "aether-deploy" % "0.14"
  ).map(
    sbtPluginExtra(_, "0.13", "2.10")
  )

