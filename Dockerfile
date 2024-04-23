FROM maven:3.6.9 as builder

ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

WORKDIR /opt/demo

COPY pom.xml .

RUN mvn dependency:go-offline

COPY ./src ./src

RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17-alpine

WORKDIR /opt/demo

COPY --from=builder /opt/demo/target/demo.jar /opt/demo

EXPOSE 7890

CMD ["java", "-jar", "app.jar"]