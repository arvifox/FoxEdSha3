plugins {
    id("java-library")
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        register<MavenPublication>("release") {
            group = "com.arvifox"
            artifactId = "asdzxc"
            version = "1.1.2"
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "scnRepo"
            url = uri(if (hasProperty("RELEASE_REPOSITORY_URL")) property("RELEASE_REPOSITORY_URL")!! else System.getenv()["RELEASE_REPOSITORY_URL"]!!)
            credentials {
                username = if (hasProperty("NEXUS_USERNAME")) (property("NEXUS_USERNAME") as String) else System.getenv()["NEXUS_USERNAME"]
                password = if (hasProperty("NEXUS_PASSWORD")) (property("NEXUS_PASSWORD") as String) else System.getenv()["NEXUS_PASSWORD"]
            }
        }
        maven {
            name = "scnRepoLocal"
            url = uri("${project.buildDir}/scnrepo")
        }
    }
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

dependencies {
//    implementation("com.madgag.spongycastle:core:1.58.0.0")
//    implementation("com.madgag.spongycastle:bcpkix-jdk15on:1.58.0.0")
    implementation("org.bouncycastle:bcpkix-jdk18on:1.76")
//    implementation("org.bouncycastle:bcprov-jdk18on:1.76")

    implementation("javax.xml.bind:jaxb-api:2.3.1")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("junit:junit:4.13.2")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
}
