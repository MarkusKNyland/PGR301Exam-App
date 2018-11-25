FROM openjdk:8-jdk-alpine
COPY target/BookApplication-exec.jar BookApplication-exec.jar
CMD ["java","-jar","BookApplication-exec.jar"]

