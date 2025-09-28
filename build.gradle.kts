plugins {
    id("java")
    id("signing")
    id("maven-publish")
    id("project-report")
    id("com.diffplug.spotless") version "7.2.1"
    id("com.github.ben-manes.versions") version "0.52.0"
    id("com.gradleup.nmcp.aggregation").version("1.0.2")
}

group = "network.lightsail"
version = "2.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
    withJavadocJar()
    withSourcesJar()
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
}

dependencies {
    implementation("network.lightsail:stellar-sdk:2.1.0")
}

tasks {
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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "stellar-sdk-android-spi"
            from(components["java"])
            pom {
                name.set("stellar-sdk-android-spi")
                description.set("Java Stellar SDK Android SPI implementation")
                url.set("https://github.com/lightsail-network/java-stellar-sdk-android-spi")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://github.com/lightsail-network/java-stellar-sdk-android-spi/blob/main/LICENSE")
                        distribution.set("https://github.com/lightsail-network/java-stellar-sdk-android-spi/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("overcat")
                        name.set("Jun Luo")
                        url.set("https://github.com/overcat")
                    }
                    organization {
                        name.set("Lightsail Network")
                        url.set("https://github.com/lightsail-network")
                    }
                }
                scm {
                    url.set("https://github.com/lightsail-network/java-stellar-sdk-android-spi")
                    connection.set("scm:git:https://github.com/lightsail-network/java-stellar-sdk-android-spi.git")
                    developerConnection.set("scm:git:ssh://git@github.com/lightsail-network/java-stellar-sdk-android-spi.git")
                }
            }
        }
    }
}

signing {
    val publishCommand = "publishAllPublicationsToCentralPortal"
    isRequired = gradle.startParameter.taskNames.contains(publishCommand)
    println("Need to sign? $isRequired")
    // https://docs.gradle.org/current/userguide/signing_plugin.html#using_in_memory_ascii_armored_openpgp_subkeys
    // export SIGNING_KEY=$(gpg2 --export-secret-keys --armor {SIGNING_KEY_ID} | grep -v '\-\-' | grep -v '^=.' | tr -d '\n')
    val signingKey = System.getenv("SIGNING_KEY")
    val signingKeyId = System.getenv("SIGNING_KEY_ID")
    val signingPassword = System.getenv("SIGNING_PASSWORD")
    if (isRequired && (signingKey == null || signingKeyId == null || signingPassword == null)) {
        throw IllegalStateException("Please set the SIGNING_KEY, SIGNING_KEY_ID, and SIGNING_PASSWORD environment variables.")
    }
    println("Signing Key ID: $signingKeyId")
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}

nmcpAggregation {
    centralPortal {
        username = System.getenv("SONATYPE_USERNAME")
        password = System.getenv("SONATYPE_PASSWORD")
        // publish manually from the portal
        publishingType = "USER_MANAGED"
        // or if you want to publish automatically
        // publishingType = "AUTOMATIC"
    }

    // Publish all projects that apply the 'maven-publish' plugin
    publishAllProjectsProbablyBreakingProjectIsolation()
}
