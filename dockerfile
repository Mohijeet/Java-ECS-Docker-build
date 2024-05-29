# Use the official Maven image to build the project
FROM maven:3.8.1-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and the source code to the working directory
COPY pom.xml .
COPY src ./src

# Run Maven package to build the project
RUN mvn clean package

# Use an official Tomcat image to run the application with Java 17
FROM tomcat:9.0.70-jdk17-temurin

# Remove the default Tomcat ROOT webapps
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the WAR file from the build stage
COPY --from=build /app/target/MYDEPLOYMENT-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port that Tomcat runs on
EXPOSE 8080

# Command to run Tomcat
CMD ["catalina.sh", "run"]
