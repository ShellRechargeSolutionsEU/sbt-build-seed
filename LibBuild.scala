package tnm

import sbt._, Keys._


object LibBuild extends AutoPlugin {

  override def requires = BasicBuild

  val allSettings = Seq(
    crossScalaVersions := Seq(ScalaVersion.curr, ScalaVersion.prev)
  )

  override lazy val projectSettings =
    allSettings
}
