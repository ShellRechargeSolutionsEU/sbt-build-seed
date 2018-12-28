package tnm

import sbt._, Keys._


sealed trait Repo {
  def snapshots: String
  def releases: String
  def url(isSnapshot: Boolean) = {
    val target = if (isSnapshot) snapshots else releases
    s"${Repo.base}/repositories/$target"
  }
}

object Repo {
  private val base = "https://nexus.thenewmotion.com/content"

  object Private extends Repo {
    override val snapshots = "snapshots"
    override val releases = "releases"
  }

  object Public extends Repo {
    override val snapshots = "snapshots-public"
    override val releases = "releases-public"
  }

  val TnmGeneral = "TNM-General" at s"$base/groups/general"
  val TnmPublic = "TNM-Public" at s"$base/groups/public"

  def publishSettings(repo: Repo) = Seq(
    publishMavenStyle := true,
    credentials := Seq(Credentials(Path.userHome / ".ivy2" / ".credentials")),
    publishTo := Some("tnm-publish-to" at repo.url(isSnapshot.value))
  )

}
