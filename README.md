# docker-javaee-demo

Jakarta EE implementation demo for Payara and WildFly using MySQL on Docker.

## 🚀 Getting Started

### 1. Requirements
* Java 17
* Maven 3.8+
* Docker & Docker Compose

### 2. Initial Setup
This project was generated using the following command:
```bash
mvn archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-webapp \
  -DarchetypeVersion=1.4 \
  -DgroupId=com.example \
  -DartifactId=docker-javaee-demo \
  -Dversion=1.0-SNAPSHOT \
  -DinteractiveMode=false
