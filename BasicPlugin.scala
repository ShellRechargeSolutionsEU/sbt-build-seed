package tnm

import sbt._, Keys._


object BasicPlugin extends AutoPlugin {

  val shellSettings = Seq(
    shellPrompt := Shell.prompt
  )

  val compilerSettings = Seq(
    scalaVersion := ScalaVersion.curr,
    resolvers := Seq(Repo.TnmGeneral),
    scalacOptions := Seq(
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xlog-reflective-calls",
      "-Ywarn-unused-import"
    ),
    parallelExecution in Compile := true,
    fork in Test := true
  )

  val publishSettings =
    Repo.publishSettings(Repo.Private) ++
    sbtrelease.ReleasePlugin.releaseSettings ++
    aether.Aether.aetherPublishSettings

  override lazy val projectSettings =
    shellSettings ++
    compilerSettings ++
    publishSettings

}
