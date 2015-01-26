package tnm

import sbt._

object Deps {
  private def inConf(configurations: String, ids: Seq[ModuleID]): Seq[ModuleID] =
    ids map (_ % configurations)

  def providedDeps(ds: ModuleID*) = inConf("provided", ds)
  def runtimeDeps(ds: ModuleID*) = inConf("runtime", ds)
  def testDeps(ds: ModuleID*) = inConf("test", ds)


  val tnmTime = "com.thenewmotion" %% "time" % "2.8"

  val specs2 = "org.specs2" %% "specs2" % "2.3.13"
  val specs2x = "ua.t3hnar.specs2x" %% "specs2x" % "1.2"



  object akka {
    val version = "2.3.6"

    def akka(lib: String) = "com.typesafe.akka" %% s"akka-$lib" % version

    val actor = akka("actor")
    val agent = akka("agent")
    val remote = akka("remote")
    val slf4j = akka("slf4j")
    val x = "com.github.t3hnar" %% "akkax" % "2.0"
    val rabbitmq = "com.thenewmotion.akka" %% "akka-rabbitmq" % "1.2.4-SNAPSHOT"
    val testkit = akka("testkit")
  }



  object spray {
    def spray(lib: String) = "io.spray" %% s"spray-$lib" % "1.3.1"

    val http = spray("http")
    val util = spray("util")
    val servlet = spray("servlet")
    val can = spray("can")
    val json = spray("json")
    val ext = "com.thenewmotion" %% "spray-ext" % "0.1.8"
  }

}
