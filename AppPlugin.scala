package tnm

import sbt._, Keys._


object AppPlugin extends AutoPlugin {

  override def requires = BasicPlugin

  lazy val publishSettings =
    Seq(
      crossPaths := false,
      crossScalaVersions := Seq(tnm.ScalaVersion.curr),
      publishArtifact in (Compile, packageDoc) := false,
      publishArtifact in (Compile, packageSrc) := false
    ) ++
    Seq(
      version := sys.props.getOrElse("application.version",
        sys.env.getOrElse("application.version", version.value))
    )

  override lazy val projectSettings =
    publishSettings

}
