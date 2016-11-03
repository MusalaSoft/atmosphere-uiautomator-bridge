# atmosphere-uiautomator-bridge
This is a special on-device component that is distributed as a regular `jar` rather than the usual Android `apk` file. It provides some missing classes on older Android API levels and an additional communication socket between the agent and the target device, so the agent can successfully recognize the on-device service and connect to its socket.

## Project setup
> This project depends on the [atmosphere-agent-device-lib](https://github.com/MusalaSoft/atmosphere-agent-device-lib), so make sure you publish `atmosphere-agent-device-lib` to your local Maven repository first.

### Setup Android SDK
[Here](https://github.com/MusalaSoft/atmosphere-docs/blob/master/setup/android_sdk.md) you may read how to setup Android SDK.

### Build the project
You can build the project using the included Gradle wrapper by running:
* `./gradlew build` on Linux/macOS<br/>
* `gradlew build` on Windows

### Publish to Maven Local
If the build is successful, also run:
* `./gradlew publishToMavenLocal`  on Linux/macOS
* `gradlew publishToMavenLocal` on Windows

to publish the jar to the local Maven repository, so other projects that depend on it can use it.
