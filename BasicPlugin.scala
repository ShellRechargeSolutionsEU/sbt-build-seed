package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin
import aether._, AetherKeys.aetherArtifact


object BasicPlugin extends AutoPlugin {

  private val javaVersion = "1.8"

  val shellSettings = Seq(
    shellPrompt := Shell.prompt
  )

  val compilerSettings = Seq(
    resolvers := Seq(Repo.TnmGeneral),
    scalaVersion := ScalaVersion.curr,
    javacOptions := Seq(
      "-source", javaVersion,
      "-target", javaVersion
    ),
    javacOptions in doc := Seq(
      "-source", javaVersion
    ),
    scalacOptions := Seq(
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xlog-reflective-calls",
      "-Xlint",
      "-Ywarn-unused-import",
      "-Ywarn-value-discard",
      s"-target:jvm-$javaVersion"
    ),
    scalacOptions in console -= "-Ywarn-unused-import",
    parallelExecution in Compile := true
  )

  val checkJavaVersionCompliance = (s: State) => {
    val installed = sys.props("java.specification.version").toDouble
    require(
      installed >= javaVersion.toDouble,
      s"Java $javaVersion is required to build this project")
    s
  }

  val miscSettings = Seq(
    cancelable in Global := true,
    parallelExecution in Test := true,
    fork in Test := true,
    fork in run := true,
    onLoad in Global :=
      checkJavaVersionCompliance compose (onLoad in Global).value
  )

  val publishSettings =
    aether.AetherPlugin.autoImport.overridePublishBothSettings ++
    Repo.publishSettings(Repo.Private)

  override lazy val projectSettings =
    shellSettings ++
    compilerSettings ++
    publishSettings ++
    miscSettings

  override val requires = ReleasePlugin && AetherPlugin
}
