# SBT Build Seed #

Adds default settings for 3 kinds of builds

- Internal Library 
- OSS Library
- Application

### How to use it in build? ###

Add following to `project/plugins.sbt`
```
resolvers += "TNM" at "http://nexus.thenewmotion.com/content/repositories/releases-public"

addSbtPlugin("com.thenewmotion" % "sbt-build-seed" % "0.4.1" )
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

However, application settings appliance has one caveat atm. 
You must explicitly set publish settings as in following snippet
```
val appModule = project.in(file("app-module"))
  .enablePlugins(AppPlugin)
  .settings(AppPlugin.publishSettings: _*)
```

### Maintainer ###
m.fedorov@thenewmotion.com