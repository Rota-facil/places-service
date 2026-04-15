FROM eclipse-temurin:jre-21-jammy

WORKDIR /app

COPY target/*.app app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]