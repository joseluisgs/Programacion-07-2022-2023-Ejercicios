plugins {
    id("java")
    kotlin("jvm") version "1.8.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Java logger
    implementation("ch.qos.logback:logback-classic:1.2.5")

    // Kotlin logger
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

    // KOTLIN OPCIÓN 1
    // JUNIT
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.0")
    // Mockk para test
    testImplementation("io.mockk:mockk:1.13.3")

    //KOTLIN OPCION 2 (unitarios propia de KOTLIN)
    //testImplementation(kotlin("test"))

    // FULL JAVA KIT
    /*  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0") // Si queremos utilizar Mock, este comentamos
        //testImplementation ("org.mockito:mockito-inline:5.1.1")
        //testImplementation ("org.mockito:mockito-junit-jupiter:5.1.1")*/

    // Simple XML
    implementation("org.simpleframework:simple-xml:2.7.1")

    //GSON
    implementation("com.google.code.gson:gson:2.8.9")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    // Moshi adapters
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")
}

tasks.test {
    useJUnitPlatform()
}

//Para hacer un JAR ejecutable
tasks.jar {
    manifest {
        // Aquí se pone el nombre del fichero que tiene el método main que queremos que se lance
        // si se llama main.kt, se pone MainKt, si se llama Otro.kt, se pone OtroKt
        // si está dentro de una carpeta se hace una ruta -> simulacionCine.SimulacionCineKt
        attributes["Main-Class"] = "Ficheros.Main"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}