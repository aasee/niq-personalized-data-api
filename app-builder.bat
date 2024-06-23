call mvn clean package -DskipTests

call docker build -t personalized-data-api .

call docker tag personalized-data-api:latest aasee/personalized-data-api:latest

call docker push aasee/personalized-data-api