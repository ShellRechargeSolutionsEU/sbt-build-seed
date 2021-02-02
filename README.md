# SBT Build Seed #

Adds default settings for 3 kinds of builds

- Internal Library 
- OSS Library
- Application

### NOTE ###

Due to an update to our nexus repository one needs to update to a version > 4.1.3
if you are using (or want to use) other New Motion libraries in you project

### How to use? ###

Add following to `project/plugins.sbt`
```
resolvers += "TNM" at "https://nexus.thenewmotion.com/content/groups/public"

addSbtPlugin("com.newmotion" % "sbt-build-seed" % "5.1.2")
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

Releases `>= 5.1.0`
- Java 8
- sbt `>= 1.3.0`

Releases `>= 2.0.1`
- Java 8
- sbt `>= 0.13.13`

Releases `>= 1.1.1` 

- sbt  `>= 0.13.9`

Releases `>= 1.0.0` 

- Java 8

Since release `1.8.0` and before `2.0.0` Java 8 support is optional. For Java 7 support use setting 
```
javaVersion := "1.7"
```

### Additional information ###
This tool does not provide default values for the `name` and the `organization`, which are required by sbt to publish to the
right path with the right name.
