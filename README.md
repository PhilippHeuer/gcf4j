# Generic Command Framework *GCF4J*

# Description

A simple wrapper to dispatch/consume events using Reactor.

# Import

Maven:

Add the repository to your pom.xml with:
```xml
<repositories>
    <repository>
      <id>jcenter</id>
      <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>
```
and the dependency: (latest, you should use the actual version here)

```xml
<dependency>
    <groupId>com.github.philippheuer.gcf4j</groupId>
    <artifactId>gcf4j</artifactId>
    <version>0.9.0</version>
    <type>pom</type>
</dependency>
```

Gradle:

Add the repository to your build.gradle with:
```groovy
repositories {
	jcenter()
}
```

and the dependency:
```groovy
compile 'com.github.philippheuer.gcf4j:gcf4j:0.9.0'
```

# License

Released under the [MIT License](./LICENSE).
