resolvers += "TNM" at "https://nexus.thenewmotion.com/content/groups/public"

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.13")
addSbtPlugin("no.arktekk.sbt" % "aether-deploy" % "0.25.0")

// Use itself as a plugin
Compile / unmanagedSourceDirectories += baseDirectory.value.getParentFile
