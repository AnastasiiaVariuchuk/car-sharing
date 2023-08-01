FROM maven:3.6.3-openjdk-17-slim AS build

WORKDIR /usr/src/java-code
COPY . /usr/src/java-code/
RUN mvn clean package

WORKDIR /usr/src/java-app
RUN cp /usr/src/java-code/target/*.jar ./app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
#ARG JAR_FILE=target/*jar
#COPY ${JAR_FILE} app.jar
#RUN mvn clean package
#ENTRYPOINT ["java", "-jar", "/app.jar"]
