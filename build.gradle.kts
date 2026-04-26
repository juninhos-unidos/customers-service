extra["commons-lang3.version"] = "3.18.0"

plugins {
	java
	application
	id("org.springframework.boot") version "3.5.13"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.13.0"
	id("org.flywaydb.flyway") version "10.20.1"
	id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1"
}

group = "br.com.customers"
version = "0.0.1-SNAPSHOT"
description = "Customer management API"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://packages.confluent.io/maven/")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")
	implementation("org.apache.avro:avro:1.12.1")
	implementation("io.confluent:kafka-avro-serializer:7.6.0")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.mapstruct:mapstruct:1.6.3")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testCompileOnly("org.projectlombok:lombok")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testAnnotationProcessor("org.projectlombok:lombok")
	constraints {
		implementation("io.swagger.core.v3:swagger-annotations:2.2.20") {
			because("Confluent/Avro dependencies pull in outdated versions that break OpenAPI generated code")
		}
	}
}

openApiGenerate {
	generatorName.set("spring")
	inputSpec.set(file("src/main/resources/spec/customer_management_api-v1.yaml").toURI().toString())
	outputDir.set(layout.buildDirectory.dir("generated/sources/openapi/v1").get().asFile.absolutePath)
	apiPackage.set("br.com.customers.api.v1")
	modelPackage.set("br.com.customers.api.v1.model")
	generateApiTests.set(false)
	generateModelTests.set(false)
	modelNameSuffix = "DTO"
	apiNameSuffix = "ApiV1"
	skipValidateSpec.set(false)
	configOptions.set(
		mapOf(
			"library" to "spring-boot",
			"useSpringBoot3" to "true",
			"serializationLibrary" to "jackson",
			"dateLibrary" to "java8",
			"interfaceOnly" to "true",
			"useTags" to "true",
			"useResponseEntity" to "true",
			"returnSuccessCode" to "true",
			"openApiNullable" to "true",
			"documentationProvider" to "springdoc",
		)
	)
}

sourceSets {
	main {
		java {
			srcDir("$rootDir/build/generated/sources/openapi/v1/src/main/java")
		}
	}
}

tasks.named("compileJava") {
	dependsOn("openApiGenerate")
}

application {
	mainClass = "br.com.customers.CustomersServiceApplication"
}

tasks.named<Jar>("jar") {
	enabled = false
}

tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
		showStandardStreams = true
		exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
	}
}
