package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin.ReleaseKeys._

object LibPlugin extends AutoPlugin {

  override def requires = BasicPlugin

  val compilerSettings = Seq(
    crossScalaVersions := Seq(ScalaVersion.curr, ScalaVersion.prev)
  )

  val releaseSettings = Seq(
    crossBuild := true
  )

  override lazy val projectSettings =
    BasicPlugin.projectSettings ++
    compilerSettings ++
    releaseSettings
}
