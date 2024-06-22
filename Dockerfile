FROM amazoncorretto:22-alpine

WORKDIR /app

COPY target/personalized-data-api-1.jar /app/personalized-data-api.jar

EXPOSE 8080

CMD ["java", "-jar", "personalized-data-api.jar"]