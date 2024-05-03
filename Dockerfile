FROM maven:3.8.3 as builder

WORKDIR /opt/demo

COPY pom.xml .

RUN mvn dependency:go-offline

COPY ./src ./src

RUN mvn clean install -D maven.test.skip=true

FROM amazoncorretto:17

ENV MYSQL_DB_PASSWORD Kendo096.

WORKDIR /opt/demo

COPY --from=builder /opt/demo/target/farsiman-evaluacion-0.0.1.jar /opt/demo/app.jar

EXPOSE 7890

CMD ["java", "-jar", "-D MY_SQL_PASSWORD=$MYSQL_DB_PASSWORD", "app.jar"]