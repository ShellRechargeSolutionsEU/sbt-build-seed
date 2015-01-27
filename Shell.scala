package tnm

import scala.util.Try
import sbt._

object Shell {
  import scala.Console._
  private def decorate(msg: String, dec: String) = s"$dec$msg$RESET"
  val prompt = (s: State) => {
    val project = decorate(Project.extract(s).currentProject.id, BOLD)
    val branch =
      Try {
        val b = "git rev-parse --abbrev-ref HEAD".!!.trim
        " ["+decorate(b, GREEN)+"]"
      } getOrElse ""

    s"$project$branch> "
  }
}
