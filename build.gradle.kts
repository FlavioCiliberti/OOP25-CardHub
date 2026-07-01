plugins {

    java

    application

    id("com.gradleup.shadow") version "9.4.2"
    id("org.danilopianini.gradle-java-qa") version "1.185.0"
}

repositories { 
    mavenCentral()
}

dependencies {

    compileOnly("com.github.spotbugs:spotbugs-annotations:4.10.2")


    implementation("com.omertron:API-OMDB:1.5")
    implementation("org.jooq:jool:0.9.15")


    val slf4jVersion = "2.0.18"
    implementation("org.slf4j:slf4j-api:$slf4jVersion")

    runtimeOnly("ch.qos.logback:logback-classic:1.5.34")

    testImplementation(platform("org.junit:junit-bom:6.1.0"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

application {

    mainClass.set("it.unibo.cardhub.App")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform() 
    testLogging {

        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.entries.toTypedArray())
        showStandardStreams = true // Show the standard output
    }
}

tasks.withType<Javadoc>().configureEach {
    isFailOnError = false
}
