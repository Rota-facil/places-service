FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY target/*.app app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]