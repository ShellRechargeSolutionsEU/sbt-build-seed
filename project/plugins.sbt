resolvers += "TNM" at "http://nexus.thenewmotion.com/content/groups/public"

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.6")
addSbtPlugin("no.arktekk.sbt" % "aether-deploy" % "0.21")

// Use itself as a plugin
unmanagedSourceDirectories in Compile += baseDirectory.value.getParentFile
