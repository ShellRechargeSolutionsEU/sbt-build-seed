package tnm

import sbt._, Keys._


object OssLibBuild extends AutoPlugin {

  override def requires = LibBuild

  override lazy val projectSettings =
    Repo.publishSettings(Repo.Public)
}
