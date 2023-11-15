java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8
rootProject.group = "com.crypticlib"
rootProject.version = "0.1.5"
//当全项目重构时更新大版本号,当添加模块或有较大更改时更新子版本号,当bug修复和功能补充时更新小版本号

var repositoryUrl = "http://repo.crypticlib.com:8081/repository/"
repositoryUrl = if (rootProject.version.toString().endsWith("SNAPSHOT")) {
    repositoryUrl.plus("maven-snapshots/")
} else {
    repositoryUrl.plus("maven-releases/")
}

plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow").version("7.1.2")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation(project(":nms"))
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveFileName.set("${rootProject.name}-${version}.jar")
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        groupId = rootProject.group as String?
    }
    repositories {
        maven {
            url = uri(repositoryUrl)
            isAllowInsecureProtocol = true
            credentials {
                username = project.findProperty("maven_username").toString()
                password = project.findProperty("maven_password").toString()
            }
        }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    version = rootProject.version
    repositories {
        mavenLocal()
        maven("https://hub.spigotmc.org/nexus/content/repositories/releases/")
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://jitpack.io")
        maven("https://repo.rosewooddev.io/repository/public/")
        maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        maven("https://repo.maven.apache.org/maven2/")
        maven("https://mvn.lumine.io/repository/maven-public/")
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://nexus.phoenixdevt.fr/repository/maven-public/")
        maven("https://r.irepo.space/maven/")
        maven("https://repo.codemc.io/repository/nms/")
        mavenCentral()
    }
    tasks {
        compileJava {
            options.encoding = "UTF-8"
        }
    }
    publishing {
        publications.create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "${rootProject.group}${project.path.replace(":", ".")}"
            artifactId = project.name
        }
        repositories {
            maven {
                url = uri(repositoryUrl)
                isAllowInsecureProtocol = true
                credentials {
                    username = project.findProperty("maven_username").toString()
                    password = project.findProperty("maven_password").toString()
                }
            }
        }
    }
    java.sourceCompatibility = JavaVersion.VERSION_1_8
    java.targetCompatibility = JavaVersion.VERSION_1_8
}