package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin
import aether._

object BasicPlugin extends AutoPlugin {

  private val javaVersion = "1.8"

  val shellSettings = Seq(
    shellPrompt := Shell.prompt
  )

  val compilerSettings = Seq(
    resolvers := Seq(Repo.TnmGeneral),
    scalaVersion := ScalaVersion.curr,
    javacOptions ++= Seq(
      "-source", javaVersion,
      "-target", javaVersion
    ),
    doc / javacOptions ++= Seq(
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
    ) ++ ((pluginCrossBuild / scalaBinaryVersion).value match {
      case v if v == "2.11" || v == "2.12" => Seq("-Ywarn-unused-import", s"-target:jvm-$javaVersion")
      case v if v == "2.13" => Seq("-Ywarn-unused:imports", s"-target:jvm-$javaVersion")
      case _ => Seq.empty
    }),
    console / scalacOptions -= "-Ywarn-unused-import",
    Compile / parallelExecution := true
  )

  val checkJavaVersionCompliance = (s: State) => {
    val installed = sys.props("java.specification.version").toDouble
    require(
      installed >= javaVersion.toDouble,
      s"Java $javaVersion is required to build this project")
    s
  }

  val miscSettings = Seq(
    Global / cancelable := true,
    Test / parallelExecution := true,
    Test / fork := true,
    run / fork := true,
    Global / onLoad :=
      checkJavaVersionCompliance compose (Global / onLoad).value
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
