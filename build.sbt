sbtPlugin := true

organization := "com.thenewmotion"
name := "sbt-build-seed"
version := "0.1.1-SNAPSHOT"

scalaVersion in Global := "2.10.4"

publishTo := Some {
  val destination = (if (isSnapshot.value) "snapshots" else "releases")+"-public"
  "TNM" at s"http://nexus.thenewmotion.com/content/repositories/$destination"
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
