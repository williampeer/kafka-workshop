import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlin_version: String by project
val kafka_version: String by project
val confluent_version: String by project

buildscript {
    val kotlin_version: String by project
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.github.jengelman.gradle.plugins:shadow:5.0.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
        classpath("org.owasp:dependency-check-gradle:7.1.1")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    }
}

plugins {
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.3.0"
    idea
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
}

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://packages.confluent.io/maven/") }
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:$kafka_version")
    implementation("io.confluent:kafka-avro-serializer:$confluent_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.apache.avro:avro:1.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

val compileKotlin: KotlinCompile by tasks
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.time.ExperimentalTime"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.ExperimentalStdlibApi"
    }
}

compileKotlin.dependsOn(tasks.generateAvroJava)

val serverOutputDir = project.layout.buildDirectory.dir("generated-api")
sourceSets {
    if (!gradle.startParameter.taskNames.any { it.toLowerCase().contains("ktLint") }) {
        val main by getting
        main.java.srcDir("${serverOutputDir.get()}/src/main/kotlin")
    }
}
