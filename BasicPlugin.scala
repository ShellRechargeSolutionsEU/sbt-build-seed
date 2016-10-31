package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin
import aether._, AetherKeys.aetherArtifact


object BasicPlugin extends AutoPlugin {

  val defaultJavaVersion = "1.8"

  object autoImport {
    val javaVersion = SettingKey[String](
      "java-version", "Source compilation and target Java version")
  }

  import autoImport._

  val shellSettings = Seq(
    shellPrompt := Shell.prompt
  )

  val compilerSettings = Seq(
    resolvers := Seq(Repo.TnmGeneral),
    scalaVersion := ScalaVersion.curr,
    javaVersion := defaultJavaVersion,
    javacOptions := Seq(
      "-source", javaVersion.value,
      "-target", javaVersion.value
    ),
    javacOptions in doc := Seq(
      "-source", javaVersion.value
    ),
    scalacOptions := Seq(
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xlog-reflective-calls",
      "-Xlint"
    ) ++
    Seq("-Ywarn-unused-import", "-target:jvm-" + javaVersion.value)
      .filter(_ => scalaVersion.value == ScalaVersion.curr),
    scalacOptions in console -= "-Ywarn-unused-import",
    parallelExecution in Compile := true
  )

  val checkJavaVersionCompliance = (s: State) => {
    val required = Project.extract(s).get(javaVersion).toDouble
    val installed = sys.props("java.specification.version").toDouble
    require(
      installed >= required,
      s"At least Java $required is required to build this project")
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
