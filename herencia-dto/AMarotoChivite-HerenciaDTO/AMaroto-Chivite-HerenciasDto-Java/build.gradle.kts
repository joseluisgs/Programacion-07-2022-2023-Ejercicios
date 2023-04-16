plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Java logger
    implementation("ch.qos.logback:logback-classic:1.2.5")

    // Simple XML
    implementation("org.simpleframework:simple-xml:2.7.1")

    //GSON
    implementation("com.google.code.gson:gson:2.8.9")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    // Moshi adapters
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")
    implementation("org.projectlombok:lombok:1.18.26")
}

tasks.test {
    useJUnitPlatform()
}