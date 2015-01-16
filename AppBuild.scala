package tnm

import sbt._, Keys._


object AppBuild extends AutoPlugin {

  override def requires = BasicBuild

  object autoImport

  private lazy val compilerSettings = Seq(
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Compile, packageSrc) := false,
    crossPaths := false
  )

  override lazy val projectSettings =
    compilerSettings
}
