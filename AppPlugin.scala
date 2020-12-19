package tnm

import sbt._, Keys._


object AppPlugin extends AutoPlugin {

  override def requires = BasicPlugin

  lazy val publishSettings =
    Seq(
      crossPaths := false,
      crossScalaVersions := Seq(scalaVersion.value),
      Compile / packageDoc / publishArtifact := false,
      Compile / packageSrc / publishArtifact := false
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
      run  / javaOptions ++= customOpts(baseDirectory.value / "dev" / "main"),
      Test / javaOptions ++= customOpts(baseDirectory.value / "dev" / "test")
    )
  }

  override lazy val projectSettings =
    publishSettings ++
    devConfigSettings

}
