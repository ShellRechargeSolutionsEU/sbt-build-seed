package tnm

import sbt._

object Deps {
  private def inConf(configurations: String, ids: Seq[ModuleID]): Seq[ModuleID] =
    ids.map(_ % configurations)

  def providedDeps(ds: ModuleID*) = inConf("provided", ds)
  def runtimeDeps(ds: ModuleID*) = inConf("runtime", ds)
  def testDeps(ds: ModuleID*) = inConf("test", ds)
}
