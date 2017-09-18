import Defaults._

sbtPlugin := true
enablePlugins(OssLibPlugin)

organization := "com.newmotion"
name := "sbt-build-seed"

crossSbtVersions := Vector("0.13.16", "1.0.1")
releaseCrossBuild := false

libraryDependencies ++= {
  val sbtV = (sbtBinaryVersion in pluginCrossBuild).value
  val scalaV = (scalaBinaryVersion in pluginCrossBuild).value

  Seq(
    "com.github.gseitz" % "sbt-release" % "1.0.6",
    "no.arktekk.sbt" % "aether-deploy" % "0.20.0"
  ).map(
    sbtPluginExtra(_, sbtV, scalaV)
  )
}

scalacOptions --= {
  (scalaBinaryVersion in pluginCrossBuild).value match {
    case v if v == "2.10" =>
      Seq(
        "-Ywarn-unused-import",
        s"-target:jvm-1.8"
      )
    case v => Nil
  }
}
