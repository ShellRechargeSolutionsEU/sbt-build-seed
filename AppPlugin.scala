package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin.ReleaseKeys._

object AppPlugin extends AutoPlugin {

  override def requires = BasicPlugin

  private lazy val compilerSettings = Seq(
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Compile, packageSrc) := false,
    crossPaths := false
  )

  private lazy val releaseSettings = Seq(
    crossBuild := false,
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Compile, packageSrc) := false
  )

  override lazy val projectSettings =
    compilerSettings ++
    releaseSettings

}
