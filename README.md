[![Build Status](https://travis-ci.org/MusalaSoft/atmosphere-uiautomator-bridge.svg?branch=master)](https://travis-ci.org/MusalaSoft/atmosphere-uiautomator-bridge) [ ![Download](https://api.bintray.com/packages/musala/atmosphere/atmosphere-uiautomator-bridge/images/download.svg) ](https://bintray.com/musala/atmosphere/atmosphere-uiautomator-bridge/_latestVersion)    
See our site for better context of this readme. [Click here](http://atmosphereframework.com/)

# atmosphere-uiautomator-bridge
This is a special on-device component that is distributed as a regular `jar` rather than the usual Android `apk` file. It provides some missing classes on older Android API levels and an additional communication socket between the agent and the target device, so the agent can successfully recognize the on-device service and connect to its socket.

## Project setup

### Setup Android SDK
[Here](https://github.com/MusalaSoft/atmosphere-docs/blob/master/setup/android_sdk.md) you may read how to setup Android SDK.

### Build the project
You can build the project using the included Gradle wrapper by running:
* `./gradlew build` on Linux/macOS<br/>
* `gradlew build` on Windows

### Making changes
If you make changes to this project and would like to use your new version in another ATMOSPHERE framework project that depends on this one, after a successful build also run:
* `./gradlew publishToMavenLocal` (Linux/macOS)
* `gradlew publishToMavenLocal` (Windows)

to publish the jar to your local Maven repository. The ATMOSPHERE framework projects are configured to use the artifact published in the local Maven repository first.
