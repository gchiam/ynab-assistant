plugins {
    application
}

group = "me.chiam.gordon"
version = "0.1.0"

application {
    mainClassName = "me.chiam.gordon.ynabassistant.HelloWorldApplication"
}

repositories {
    mavenCentral()
}

object Versions {
    const val dropwizardVersion = "1.3.11"
    const val junitVersion = "5.4.2"
}

dependencies {
    compile("io.dropwizard", "dropwizard-core", Versions.dropwizardVersion)
    testCompile("org.junit.jupiter", "junit-jupiter-api", Versions.junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", Versions.junitVersion)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

