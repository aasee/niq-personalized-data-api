# niq-personalized-data-api

# Run tests

1. First make sure docker is running
2. Run the following command in the root directory of the project

```bash
./mvnw test
```

This will create a docker container with a postgreSQL database and run the tests.

# Pre-requisites to run application

## Run a postgreSQL database in a container

```bash
docker run --name postgres -e POSTGRES_PASSWORD=toor -d -p 5433:5432 postgres
```

## Connect to the database

```bash
docker exec -it postgres psql -U postgres
```

## Create a database

```sql
CREATE DATABASE personalizeddata;
```

## Update the application.properties file

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/personalizeddata
```