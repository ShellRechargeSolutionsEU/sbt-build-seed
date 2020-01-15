package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin
import aether._

object BasicPlugin extends AutoPlugin {

  private val javaVersion = "1.8"

  val shellSettings = Seq(
    shellPrompt := Shell.prompt
  )

  lazy val scalacBackendOptions = Seq(
    "-Ybackend-parallelism", sys.runtime.availableProcessors().toString, // Maximum worker threads for backend
    "-Ybackend-worker-queue", "10" // Backend threads worker queue
  )

  lazy val scalacTargetJvm = s"-target:jvm-$javaVersion"

  val compilerSettings = Seq(
    resolvers := Seq(Repo.TnmGeneral),
    scalaVersion := ScalaVersion.curr,
    javacOptions ++= Seq(
      "-source", javaVersion,
      "-target", javaVersion
    ),
    javacOptions in doc ++= Seq(
      "-source", javaVersion
    ),
    scalacOptions ++= Seq(
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xlog-reflective-calls",
      "-Xlint",
      "-Ywarn-value-discard"
    ) ++ ((scalaBinaryVersion in pluginCrossBuild).value match {
      case "2.11" => Seq("-Ywarn-unused-import", scalacTargetJvm)
      case "2.12" => Seq("-Ywarn-unused-import", scalacTargetJvm) ++ scalacBackendOptions
      case "2.13" => Seq("-Ywarn-unused:imports", scalacTargetJvm) ++ scalacBackendOptions
      case _ => Seq.empty
    }),
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
