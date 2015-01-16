# SBT Build Seed #

Adds default settings for 3 kinds of builds
* Internal Library 
* OSS Library
* Application

### How to use it in build? ###

Add following to `project/plugins.sbt`
```
resolvers += "TNM" at "http://nexus.thenewmotion.com/content/repositories/releases-public"

addSbtPlugin("com.thenewmotion" % "sbt-build-seed" % "0.1.0" )
```

Then in your build file of choice
```
val myModule = (project in file("my-module"))
  .enablePlugins(AppBuild)
```
or
```
val myModule = (project in file("my-module"))
  .enablePlugins(LibBuild)
```
or
```
val myModule = (project in file("my-module"))
  .enablePlugins(OssLibBuild)
```