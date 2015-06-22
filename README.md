# SBT Build Seed #

Adds default settings for 3 kinds of builds

- Internal Library 
- OSS Library
- Application


### For best experience before using it perform following ###
Install [alternative script for sbt](https://github.com/paulp/sbt-extras) 

Setup usage of latest SBT available with 

```alias sbt="sbt -sbt-force-latest"``` 


### How to use it in build? ###

Add following to `project/plugins.sbt`
```
resolvers += "TNM" at "http://nexus.thenewmotion.com/content/groups/public"

addSbtPlugin("com.thenewmotion" % "sbt-build-seed" % "0.6.2" )
```

For library use:
```
val libModule = project.in(file("lib-module"))
  .enablePlugins(LibPlugin)
```

For OSS library use:
```
val ossLibModule = project.in(file("oss-lib-module"))
  .enablePlugins(OssLibPlugin)
```

### Maintainer ###
m.fedorov@thenewmotion.com
