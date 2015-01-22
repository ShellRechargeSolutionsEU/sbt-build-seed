package tnm

import sbt._, Keys._


object OssLibPlugin extends AutoPlugin {

  override def requires = LibPlugin

  val publishSettings = Repo.publishSettings(Repo.Public)

  override lazy val projectSettings =
    publishSettings
}
