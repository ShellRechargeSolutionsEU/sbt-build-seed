package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin.autoImport.releaseCrossBuild

object LibPlugin extends AutoPlugin {

  override def requires = BasicPlugin

  val compilerSettings = Seq(
    crossScalaVersions := Seq(ScalaVersion.curr, ScalaVersion.prev)
  )

  val releaseSettings = Seq(
    releaseCrossBuild := true
  )

  override lazy val projectSettings =
    BasicPlugin.projectSettings ++
    compilerSettings ++
    releaseSettings
}
