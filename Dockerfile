#FROM openjdk:8-jdk-alpine
#COPY target/BookApplication-exec.jar BookApplication-exec.jar
#CMD ["java","-jar","BookApplication-exec.jar"]

FROM maven:3.6-jdk-8 as maven
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

COPY ./src ./src
RUN mvn package

FROM openjdk:8-jre-alpine
#WORKDIR /BookRest
COPY --from=maven target/BookApplication-exec.jar BookApplication-exec.jar
CMD ["java","-jar","BookApplication-exec.jar"]
