import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.io.github.mew22.kmp.lib.plugin)
    alias(libs.plugins.io.github.mew22.cmp.plugin)
    id("com.vanniktech.maven.publish") version "0.30.0"
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(libs.sha2)
    }
}

group = "io.github.mew22"
version = "1.0.0"

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "multiavatar", version.toString())

    pom {
        name = "Multiavatar library"
        description = "This is a Kotlin transcription of the original Multiavatar script compatible with Compose Multiplatform."
        inceptionYear = "2025"
        url = "https://github.com/mew22/Multiavatar"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "mew22"
                name = "Sebastien Delaherche"
                url = "https://github.com/mew22/Multiavatar"
            }
        }
        scm {
            url = "https://github.com/mew22/Multiavatar"
            connection = "scm:git:git://github.com/mew22/Multiavatar.git"
            developerConnection = "scm:git:ssh://git@github.com/mew22/Multiavatar.git"
        }
    }
}