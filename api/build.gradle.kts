dependencies {
    // OpenAPI
    api(group = "io.opentracing", name = "opentracing-api", version = "0.33.0")
}

publishing.publications.withType<MavenPublication> {
    pom {
        name.set("API Module")
        description.set("GCF - API")
    }
}