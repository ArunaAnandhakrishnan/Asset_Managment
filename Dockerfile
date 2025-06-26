# Stage 1: Build the JAR
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /assetmanagement
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /assetmanagement
COPY --from=build /assetmanagement/target/assetmanagementtool-0.0.1.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
