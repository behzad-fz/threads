plugins {
    id("java")
}

group = "org.behzadfz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("com.github.ben-manes.caffeine:caffeine:2.8.8")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.behzadfz.Main"
    }
}