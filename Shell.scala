package tnm

import scala.util.Try
import sbt._

object Shell {
  import scala.Console._
  private def decorate(msg: String, dec: String) = s"$dec$msg$RESET"
  val prompt = (s: State) => {
    val project = decorate(Project.extract(s).get(Keys.name), BOLD)
    val branch =
      Try {
        val branch = scala.sys.process.Process("git rev-parse --abbrev-ref HEAD").!!.trim
        " ["+decorate(branch, GREEN)+"]"
      } getOrElse ""

    s"$project$branch> "
  }
}
