FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY Lab_1 ./Lab_1
COPY Lab_2 ./Lab_2
COPY Lab_3 ./Lab_3
COPY Servidor ./Servidor
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/laboratorios-backend.jar /app/laboratorios-backend.jar
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "/app/laboratorios-backend.jar"]
