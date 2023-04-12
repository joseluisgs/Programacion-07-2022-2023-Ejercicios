import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies{
    testImplementation(kotlin("test"))
    //Mockk
    testImplementation("io.mockk:mockk:1.13.4")
    //Logger
    implementation("ch.qos.logback:logback-classic:1.4.6")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    //Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    //Simple XML
    implementation("org.simpleframework:simple-xml:2.7.1")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}