package tnm

import sbt._, Keys._

object Deps {
  implicit class PimpedProject(val p: Project) extends AnyVal {
    def dependsOn(file: File) =
      p.dependsOn(file.getAbsoluteFile.toURI)

    def dependsOn(ds: ModuleID*) =
      p.settings(
        libraryDependencies ++= ds)

    def dependsOn(scope: String, ds: ModuleID*) =
      p.settings(
        libraryDependencies ++= ds.map(_ % scope))
  }
}
