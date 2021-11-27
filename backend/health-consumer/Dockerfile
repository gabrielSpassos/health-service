FROM java:8
ADD /build/libs/health-consumer-0.0.1-SNAPSHOT.jar /health-consumer.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/health-consumer.jar"]