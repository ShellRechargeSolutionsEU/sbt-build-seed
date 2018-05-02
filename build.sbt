import Defaults._
import aether.AetherKeys.aetherCoordinates

sbtPlugin := true
enablePlugins(OssLibPlugin)

organization := "com.newmotion"
name := "sbt-build-seed"

crossSbtVersions := Vector("0.13.17", "1.1.2")
releaseCrossBuild := false

libraryDependencies ++= {
  val sbtV = (sbtBinaryVersion in pluginCrossBuild).value
  val scalaV = (scalaBinaryVersion in pluginCrossBuild).value

  Seq(
    "com.github.gseitz" % "sbt-release" % "1.0.8",
    "no.arktekk.sbt" % "aether-deploy" % "0.21",
    "org.lyranthe.sbt" % "partial-unification" % "1.1.0"
  ).map(
    sbtPluginExtra(_, sbtV, scalaV)
  )
}

scalacOptions --= {
  (scalaBinaryVersion in pluginCrossBuild).value match {
    case v if v == "2.10" =>
      Seq(
        "-Ywarn-unused-import",
        s"-target:jvm-1.8"
      )
    case _ => Nil
  }
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
