FROM amazoncorretto:22-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar to the container
COPY target/personalized-data-api-1.jar /app/personalized-data-api.jar

# Set default environment variables
ENV PORT=9090
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=toor
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
ENV SPRING_DATASOURCE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_DATASOURCE_SHOW_SQL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

EXPOSE 9090

CMD ["java", "-jar", "personalized-data-api.jar"]