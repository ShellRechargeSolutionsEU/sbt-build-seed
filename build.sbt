import Defaults._
import aether.AetherKeys.aetherCoordinates

sbtPlugin := true
enablePlugins(OssLibPlugin)

organization := "com.newmotion"
name := "sbt-build-seed"

crossSbtVersions := Vector("0.13.18", "1.3.0")
releaseCrossBuild := false

scalaVersion := tnm.ScalaVersion.prev

libraryDependencies ++= {
  val sbtV = (sbtBinaryVersion in pluginCrossBuild).value
  val scalaV = (scalaBinaryVersion in pluginCrossBuild).value

  Seq(
    "com.github.gseitz" % "sbt-release" % "1.0.11",
    "no.arktekk.sbt" % "aether-deploy" % "0.21"
  ).map(
    sbtPluginExtra(_, sbtV, scalaV)
  )
}

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  releaseStepCommandAndRemaining("^ test"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("^ publish"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
