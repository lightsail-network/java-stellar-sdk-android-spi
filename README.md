# java-stellar-sdk-android-spi

[![Test and Deploy](https://github.com/lightsail-network/java-stellar-sdk-android-spi/actions/workflows/test-deploy.yml/badge.svg?branch=main)](https://github.com/lightsail-network/java-stellar-sdk-android-spi/actions/workflows/test-deploy.yml)
[![Maven Central Version](https://img.shields.io/maven-central/v/network.lightsail/stellar-sdk-android-spi)](https://central.sonatype.com/artifact/network.lightsail/stellar-sdk-android-spi)

The goal of this library is to enable users to conveniently integrate the Java Stellar SDK into lower versions of the
Android platform. In this context, lower versions refer to Android API level 23 to 27. If your minSdk is set to 28 or
higher, you do not need to include this library.

## Installation

### Apache Maven

```xml
<dependency>
    <groupId>network.lightsail</groupId>
    <artifactId>stellar-sdk-android-spi</artifactId>
    <version>2.2.3</version>
</dependency>
```

### Gradle

```groovy
implementation 'network.lightsail:stellar-sdk-android-spi:2.2.3'
```

The versions of `java-stellar-sdk` and `java-stellar-sdk-android-spi` should be maintained at the same version.

You can find instructions on how to install this dependency using alternative package
managers [here](https://central.sonatype.com/artifact/network.lightsail/stellar-sdk-android-spi).

### JAR

Download the latest jar from the GitHub
repo's [releases tab](https://github.com/lightsail-network/java-stellar-sdk-android-spi/releases). Add the `jar` package
to your project according to how your environment is set up.
