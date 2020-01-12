import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.60"
    kotlin("plugin.serialization") version "1.3.60"
    id("org.jetbrains.dokka") version "0.10.0"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
    `build-scan`
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    publishAlways()
}

group = "org.mechdancer"
version = "0.1.0-dev-1"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-runtime", "0.14.0")

    val vertVersion = "3.8.4"
    implementation("io.vertx:vertx-web:$vertVersion")
    implementation("io.vertx:vertx-web-client:$vertVersion")
    implementation("io.vertx:vertx-lang-kotlin-coroutines:$vertVersion")

    testImplementation("junit", "junit", "+")
    testImplementation(kotlin("test-junit"))
}

tasks.dokka {
    outputFormat = "javadoc"
    outputDirectory = "$buildDir/javadoc"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xuse-experimental=kotlin.Experimental")
    }
}

val doc = tasks.register<Jar>("javadocJar") {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.dokka)
}

val sources = tasks.register<Jar>("sourcesJar") {
    group = JavaBasePlugin.BUILD_TASK_NAME
    description = "Creates sources jar"
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val fat = tasks.register<Jar>("fatJar") {
    group = JavaBasePlugin.BUILD_TASK_NAME
    description = "Packs binary output with dependencies"
    archiveClassifier.set("all")
    from(sourceSets.main.get().output)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

tasks.register("allJars") {
    group = JavaBasePlugin.BUILD_TASK_NAME
    description = "Assembles all jars in one task"
    dependsOn(doc, sources, fat, tasks.jar)
}

val rename = tasks.register("renamePomFile") {
    dependsOn(tasks.publishToMavenLocal)
    doLast {
        val path = "${buildDir.absolutePath}/publications/maven/"
        val old = File(path + "pom-default.xml")
        val f = File("$path${project.name}-$version.pom")
        old.renameTo(f)
    }
}

tasks.bintrayUpload.configure {
    dependsOn(rename)
}

bintray {
    user = "berberman"
    key = System.getenv("BintrayToken")
    setConfigurations("archives")
    val v = version.toString()
    val url = "https://github.com/MechDancer/dingtalk-bot"
    publish = true
    pkg.apply {
        name = project.name
        desc = "a kotlin api of dingtalk bot."
        repo = "maven"
        userOrg = "mechdancer"
        githubRepo = "MechDancer/dingtalk"
        vcsUrl = "$url.git"
        issueTrackerUrl = "$url/issues"
        publicDownloadNumbers = true
        setLicenses("WTFPL")
        version.apply {
            name = v
            vcsTag = v
            websiteUrl = "$url/releases/tag/$v"
        }
    }
}

publishing {

    repositories {
        maven("$buildDir/repo")
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/MechDancer/dingtalk-bot")
            credentials {
                username = "MechDancerProject"
                password = System.getenv("GitHubToken")
            }
        }
    }


    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

artifacts {
    add("archives", tasks.jar)
    add("archives", fat)
    add("archives", sources)
    add("archives", doc)
    add("archives", File("${buildDir.absolutePath}/publications/maven/${project.name}-$version.pom"))
}
