# SBT Build Seed #

Adds default settings for 3 kinds of builds

- Internal Library 
- OSS Library
- Application


### How to use? ###

Add following to `project/plugins.sbt`
```
resolvers += "TNM" at "http://nexus.thenewmotion.com/content/groups/public"

addSbtPlugin("com.thenewmotion" % "sbt-build-seed" % "1.5.0")
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

### Recommendations ###
Instead of standard sbt launcher use [sbt-extras](https://github.com/paulp/) and add following alias to your shell init script

`alias sbt="sbt -sbt-force-latest"`

If you keep updating sbt-extras you'll always have up-to-date sbt at your disposal.

### Requirements ###
Releases `>= 1.1.1` 

- sbt  `>= 0.13.9`

Releases `>= 1.0.0` 

- Java 8


For Java 7 support use release `0.9.3`.


### Maintainer ###
m.fedorov@thenewmotion.com
