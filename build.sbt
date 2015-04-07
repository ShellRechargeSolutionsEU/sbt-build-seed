import Defaults._
import sbtrelease.ReleasePlugin.ReleaseKeys.crossBuild

sbtPlugin := true
enablePlugins(LibPlugin)

organization := "com.thenewmotion"
name := "sbt-build-seed"

scalaVersion := tnm.ScalaVersion.prev
crossBuild := false

tnm.Repo.publishSettings(tnm.Repo.Public)

libraryDependencies ++=
  Seq(
    "org.scala-sbt" % "sbt" % "0.13.8") ++
  Seq(
    "com.github.gseitz" % "sbt-release" % "0.8.5",
    "no.arktekk.sbt" % "aether-deploy" % "0.11"
  ).map(
    sbtPluginExtra(_, "0.13", "2.10")
  )

