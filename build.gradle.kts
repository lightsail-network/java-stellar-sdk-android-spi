plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("project-report")
    id("com.diffplug.spotless") version "6.21.0"
    id("com.github.ben-manes.versions") version "0.48.0"
}

group = "stellar"
version = "0.41.1"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

spotless {
    java {
        importOrder("java", "javax", "org.stellar")
        removeUnusedImports()
        googleJavaFormat()
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // TODO: update version from jitpack
    implementation("com.github.overcat:java-stellar-sdk:503a82cd894de9c166316e15d6a0d7bcb62fcb8f")
}

tasks {
    jar {
        manifest {
            attributes["Implementation-Title"] = "stellar-sdk-android-spi"
            attributes["Implementation-Version"] = version
        }
        archiveFileName = "stellar-sdk-android-spi.jar"
    }

    register<Copy>("updateGitHook") {
        from("scripts/pre-commit.sh") { rename { it.removeSuffix(".sh") } }
        into(".git/hooks")
        doLast {
            file(".git/hooks/pre-commit").setExecutable(true)
        }
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    compileTestJava {
        options.encoding = "UTF-8"
    }
}

artifacts {
    archives(tasks.jar)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("java-stellar-sdk-android-spi")
                description.set("Java Stellar SDK Android SPI implementation")
                url.set("https://github.com/stellar/java-stellar-sdk-android-spi")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://github.com/stellar/java-stellar-sdk-android-spi/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("stellar")
                        name.set("Stellar Development Foundation")
                    }
                }
            }
        }
    }
}
