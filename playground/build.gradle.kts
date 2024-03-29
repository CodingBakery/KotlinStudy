import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import java.util.regex.Pattern.compile

plugins {
    kotlin("kapt") version "1.5.30"
    kotlin("jvm") version "1.5.10"
    java
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib")
//    implementation(kotlin("stdlib"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    testImplementation("io.kotest:kotest-runner-junit5:5.3.2")
    testImplementation("io.kotest:kotest-assertions-core:5.3.2")
    testImplementation("io.kotest:kotest-property:5.3.2")
    testImplementation("io.mockk:mockk:1.12.4")

    implementation("org.mapstruct:mapstruct:1.5.1.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.1.Final")
    kaptTest("org.mapstruct:mapstruct-processor:1.5.1.Final")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.register("prepareKotlinBuildScriptModel"){}