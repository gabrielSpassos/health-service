FROM java:8
ADD /build/libs/health-service-0.0.1-SNAPSHOT.jar /health-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/health-service.jar"]