val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val kotlinSerializationVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
}

group = "dev.pott"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$kotlinSerializationVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("it.skrape:skrapeit:1.1.1")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")
