import Defaults._

sbtPlugin := true
enablePlugins(OssLibPlugin)

organization := "com.newmotion"
name := "sbt-build-seed"

releaseCrossBuild := false

scalaVersion := tnm.ScalaVersion.prev

libraryDependencies ++= {
  val sbtV = (pluginCrossBuild / sbtBinaryVersion).value
  val scalaV = (pluginCrossBuild / scalaBinaryVersion).value

  Seq(
    "com.github.gseitz" % "sbt-release" % "1.0.13",
    "no.arktekk.sbt" % "aether-deploy" % "0.25.0"
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
