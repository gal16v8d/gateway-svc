FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy any JAR file from the target directory to the container
COPY target/*.jar gateway-svc.jar

# Expose the port your Spring Boot app listens on (change as needed)
EXPOSE 8762

# Command to run the application
CMD ["java", "-jar", "gateway-svc.jar"]