package tnm

import sbt._, Keys._


object OssLibPlugin extends AutoPlugin {

  override def requires = LibPlugin

  val compilerSettings = Seq(
    resolvers := Seq(Repo.TnmPublic))

  val publishSettings = Repo.publishSettings(Repo.Public)

  override lazy val projectSettings =
    compilerSettings ++
    publishSettings
}
