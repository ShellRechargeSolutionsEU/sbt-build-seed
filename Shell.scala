package tnm

import sbt._

object Shell {
  import scala.Console._
  private def decorate(msg: String, dec: String) = s"$dec$msg$RESET"
  val prompt = (s: State) => {
    "%s [%s]> " format (
      decorate(Project.extract(s).currentProject.id, BOLD),
      decorate(("git rev-parse --abbrev-ref HEAD".!!).trim, GREEN)
    )
  }
}
