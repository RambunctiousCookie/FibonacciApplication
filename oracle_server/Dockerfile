# Use a base image with Java pre-installed
FROM openjdk:19

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file containing your web service to the container
COPY OracleSubmission-0.0.1-SNAPSHOT.jar .

# Expose the port on which your service will listen
EXPOSE 8000

# Specify the command to run your web service
CMD ["java", "-jar", "OracleSubmission-0.0.1-SNAPSHOT.jar"]
