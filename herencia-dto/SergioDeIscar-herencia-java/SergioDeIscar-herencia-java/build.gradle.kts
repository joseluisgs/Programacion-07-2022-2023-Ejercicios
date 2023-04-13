plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Logger
    implementation("ch.qos.logback:logback-classic:1.4.6")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")

    // Lombok me da problemas más que otra cosa
    // implementation("org.projectlombok:lombok:1.18.26")

    // Simple XML
    implementation("org.simpleframework:simple-xml:2.7.1")
}

tasks.test {
    useJUnitPlatform()
}