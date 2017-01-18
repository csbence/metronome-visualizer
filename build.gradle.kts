import org.gradle.api.Task
import org.gradle.script.lang.kotlin.*
import kotlin.properties.ReadOnlyProperty
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import kotlin.io.print
import kotlin.io.println

buildscript {

    repositories {
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
    }
}

plugins {
    application
}

apply {
    plugin("kotlin2js")
}

configure<ApplicationPluginConvention> {
    mainClassName = "edu.unh.cs.metronome.MetronomeVisualizerKt"
}

val build = tasks.getByName("build")
build.doLast {
    print(configurations.compile)
//    build.configurations.compile.each { file ->
//        copy {
//            includeEmptyDirs = false
//
//            from zipTree(file.absolutePath)
//            into "${projectDir}/web/js"
//            include { fileTreeElement ->
//                def path = fileTreeElement.path
//                        path.endsWith(".js") && (path.startsWith("META-INF/resources/") || !path.startsWith("META-INF/"))
//            }
//        }
//    }
}

val compileKotlin2Js = tasks.getByName("compileKotlin2Js")
//compileKotlin2Js.kotlinOptions.outputFile = "web/js/metronome-visualizer.js"

compileKotlin2Js.apply {
//    kotlinOptions.outputFile = "web/js/metronome-visualizer.js"
}

task("nope") {
    doLast {
        println("hello world ${compileKotlin2Js.outputFile}")
    }
}


//val mainSourceSet = sourceSets.getByName("main")!!
//(mainSourceSet as HasConvention).convention.getPlugin<KotlinSourceSet>().apply {
//    kotlin.srcDir("src/main/kotlin")
//}

//sourceSets {
//    main.kotlin.srcDirs += "src/main/kotlin"
//}

repositories {
    gradleScriptKotlin()
    mavenCentral()
}

dependencies {
    compile(kotlinModule("stdlib"))
    compile(kotlinModule("js-library"))
    testCompile("junit:junit:4.12")
}