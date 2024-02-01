import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.8"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

val kotlinSdkVersion = "1.0.41"
val smithyKotlinVersion = "1.0.10"
dependencies {
	implementation("aws.sdk.kotlin:s3:$kotlinSdkVersion")
	implementation("aws.sdk.kotlin:s3control:$kotlinSdkVersion")
	implementation("aws.sdk.kotlin:sts:$kotlinSdkVersion")
	implementation("aws.sdk.kotlin:secretsmanager:$kotlinSdkVersion")
	implementation("aws.smithy.kotlin:http-client-engine-okhttp:$smithyKotlinVersion")
	implementation("aws.smithy.kotlin:http-client-engine-crt:$smithyKotlinVersion")
	implementation("aws.smithy.kotlin:aws-signing-crt:$smithyKotlinVersion")
	implementation("aws.smithy.kotlin:http-auth-aws:$smithyKotlinVersion")
	implementation("com.squareup.okhttp3:okhttp:4.12.0")
//	implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
	implementation("com.google.code.gson:gson:2.10")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}
