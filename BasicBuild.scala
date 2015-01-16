package tnm

import sbt._, Keys._


object BasicBuild extends AutoPlugin {

  private val shellSettings = Seq(
    shellPrompt := Shell.prompt
  )

  private val compilerSettings = Seq(
    scalaVersion := ScalaVersion.curr,
    resolvers := Seq(Repo.TnmGeneral),
    scalacOptions := Seq(
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xlog-reflective-calls"
    ),
    parallelExecution in Compile := true,
    fork in Test := true
  )

  val tnmDefaultPublishSettings =
    Repo.publishSettings(Repo.Private)

  val allBasicSettings =
    shellSettings ++
    compilerSettings ++
    tnmDefaultPublishSettings

  override lazy val projectSettings =
    allBasicSettings

}
