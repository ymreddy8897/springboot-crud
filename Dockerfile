# Use lightweight Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/EcommerceApp-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 9090

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]