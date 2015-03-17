package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin.ReleaseKeys._

object AppPlugin extends AutoPlugin {

  override def requires = BasicPlugin

  lazy val publishSettings =
    aether.Aether.aetherPublishSettings ++
    Seq(
      crossPaths := false,
      publishArtifact in (Compile, packageDoc) := false,
      publishArtifact in (Compile, packageSrc) := false
    ) ++
    Seq(
      version := sys.props.getOrElse("application.version", 
        sys.env.getOrElse("application.version", "0.1-SNAPSHOT"))
    )
    
  override lazy val projectSettings =
    publishSettings

}
