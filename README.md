# java-stellar-sdk-android-spi

[![Test and Deploy](https://github.com/stellar/java-stellar-sdk-android-spi/actions/workflows/test-deploy.yml/badge.svg?branch=main)](https://github.com/stellar/java-stellar-sdk-android-spi/actions/workflows/test-deploy.yml)
[![](https://jitpack.io/v/stellar/java-stellar-sdk-android-spi.svg)](https://jitpack.io/#stellar/java-stellar-sdk-android-spi)

The goal of this library is to enable users to conveniently integrate the Java Stellar SDK into lower versions of the Android platform. In this context, lower versions refer to Android API level 23 to 27. If your minSdk is set to 28 or higher, you do not need to include this library.

## Installation

### Maven

Use [jitpack.io](https://jitpack.io)'s Maven repository:

```
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.stellar:java-stellar-sdk:{version}'
    implementation 'com.github.stellar:java-stellar-sdk-android-spi:{version}'
}
```

The versions of `java-stellar-sdk` and `java-stellar-sdk-android-spi` should be maintained at the same version.

The list of versions to install can be found in the [Releases](https://github.com/stellar/java-stellar-sdk-android-spi/releases) section. More information can be found in [jitpack.io docs](https://jitpack.io/docs/).

### JAR

Download the latest jar from the GitHub repo's [releases tab](https://github.com/stellar/java-stellar-sdk-android-spi/releases). Add the `jar` package to your project according to how your environment is set up.
