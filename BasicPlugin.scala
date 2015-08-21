package tnm

import sbt._, Keys._
import sbtrelease.ReleasePlugin
import aether.AetherPlugin


object BasicPlugin extends AutoPlugin {

  Logging.initFactory()

  require(
    sys.props("java.specification.version").toDouble > 1.7,
    "Java 8 is required to build this project")

  val shellSettings = Seq(
    shellPrompt := Shell.prompt
  )

  val compilerSettings = Seq(
    resolvers := Seq(Repo.TnmGeneral),
    scalaVersion := ScalaVersion.curr,
    ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },
    javacOptions := Seq(
      "-source", "1.8",
      "-target", "1.8"
    ),
    javacOptions in doc := Seq(
      "-source", "1.8"
    ),
    scalacOptions := Seq(
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xlog-reflective-calls",
      "-target:jvm-1.8"
    ),
    parallelExecution in Compile := true
  )

  val miscSettings = Seq(
    cancelable in Global := true,
    parallelExecution in Test := true,
    fork in Test := true,
    fork in run := true
  )

  val publishSettings =
    aether.AetherPlugin.autoImport.overridePublishBothSettings ++
    Repo.publishSettings(Repo.Private)

  override lazy val projectSettings =
    shellSettings ++
    compilerSettings ++
    publishSettings ++
    miscSettings

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
