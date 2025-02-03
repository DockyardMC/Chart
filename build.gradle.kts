plugins {
    kotlin("jvm") version "1.9.23"
}

group = "io.github.dockyardmc.lodestone"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.ktor:ktor-server-netty:2.3.12")
    compileOnly("io.ktor:ktor-network:2.3.12")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}