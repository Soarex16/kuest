import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    application
}

group = "com.soarex"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.hexworks.zircon:zircon.core-jvm:2021.1.0-RELEASE")
    implementation("org.hexworks.zircon:zircon.jvm.swing:2021.1.0-RELEASE")
}

application {
    mainClass.set("com.soarex.kuest.game.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}