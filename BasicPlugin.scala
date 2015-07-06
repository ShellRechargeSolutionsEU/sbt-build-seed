package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin
import aether.AetherPlugin

object BasicPlugin extends AutoPlugin {

  Logging.initFactory()

  val shellSettings = Seq(
    shellPrompt := Shell.prompt
  )

  val compilerSettings = Seq(
    scalaVersion := ScalaVersion.curr,
    ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },
    resolvers := Seq(Repo.TnmGeneral),
    javacOptions := Seq(
      "-source", "1.7",
      "-target", "1.7"
    ),
    javacOptions in doc := Seq(
      "-source", "1.7"
    ),
    scalacOptions := Seq(
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xlog-reflective-calls",
      "-target:jvm-1.7"
    ),
    parallelExecution in Compile := true,
    fork in Test := true
  )

  val publishSettings =
    aether.AetherPlugin.autoImport.overridePublishBothSettings ++
    Repo.publishSettings(Repo.Private)

  override lazy val projectSettings =
    shellSettings ++
    compilerSettings ++
    publishSettings

  override val requires = ReleasePlugin && AetherPlugin
}

private[tnm] object Logging {
  def initFactory() {
    def cls(fqn: String) = this.getClass.getClassLoader.loadClass(fqn)

    cls("org.slf4j.LoggerFactory")
    .getMethod("getLogger", cls("java.lang.String"))
    .invoke(/*since it's a static method*/ null, "ROOT")
  }
}
