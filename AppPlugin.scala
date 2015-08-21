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

  lazy val devConfigSettings = {
    def customOpts(dir: File) = {
      Seq(
        "logback.configurationFile" -> dir / "logback.xml",
        "config.file" -> dir / "application.conf"
      ).collect {
        case (key, file) if file.exists => s"-D$key=$file"
      }
    }

    Seq(
      javaOptions in run := customOpts(baseDirectory.value / "dev" / "main"),
      javaOptions in Test := customOpts(baseDirectory.value / "dev" / "test")
    )
  }

  override lazy val projectSettings =
    publishSettings ++
    devConfigSettings

}
