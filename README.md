# SBT Build Seed #

Adds default settings for 3 kinds of builds

- Internal Library 
- OSS Library
- Application


### How to use? ###

Add following to `project/plugins.sbt`
```
resolvers += "TNM" at "http://nexus.thenewmotion.com/content/groups/public"

addSbtPlugin("com.thenewmotion" % "sbt-build-seed" % "1.2.0")
```

To develop a library in your build.sbt use:
```
enablePlugins(LibPlugin)
```

To develop an OSS library in your build.sbt use:
```
enablePlugins(OssLibPlugin)
```

To develop an application in your build.sbt use:
```
enablePlugins(AppPlugin)
```


### Compatibility ###
Since plugin version `1.0.0` minimum required Java version is 8.
If Java 7 support is needed use version `0.9.3`


### Maintainer ###
m.fedorov@thenewmotion.com
