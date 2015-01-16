package tnm

import sbt._, Keys._


sealed trait Repo {
  def snapshots = "snapshots"
  def releases = "releases"
  def url(isSnapshot: Boolean) =
    s"${Repo.base}/repositories/"+(if (isSnapshot) snapshots else releases)
}

object Repo {
  private val base = "http://nexus.thenewmotion.com/content"
  object Private extends Repo
  object Public extends Repo {
    override val snapshots = "snapshots-public"
    override val releases = "releases-public"
  }

  val TnmGeneral = "TNM" at s"$base/groups/general"

  def publishSettings(repo: Repo) = Seq(
    publishMavenStyle := true,
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    publishTo := Some("tnm-publish-to" at repo.url(isSnapshot.value))
  )

}
