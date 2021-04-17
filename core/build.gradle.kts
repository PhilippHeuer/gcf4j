dependencies {
    // Project Modules
    api(project(":api"))

    // Trace
    api(group = "io.opentracing", name = "opentracing-mock", version = "0.33.0")

    // Commons
    api(group = "org.apache.commons", name = "commons-lang3", version = "3.12.0")
    api(group = "org.apache.commons", name = "commons-collections4", version = "4.4")

    // HttpClient
    api(group = "com.squareup.okhttp3", name = "okhttp", version = "4.9.1")

    // Jackson
    api(group = "com.fasterxml.jackson.core", name = "jackson-annotations", version = "2.12.2")

    // Arguments
    implementation(group = "commons-cli", name = "commons-cli", version = "1.4")
}

publishing.publications.withType<MavenPublication> {
    pom {
        name.set("Core Module")
        description.set("GCF - Core")
    }
}
