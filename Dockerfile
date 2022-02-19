FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/*.jar app.jar
RUN adduser -u 2222 nonroot --disabled-password
USER nonroot
ENTRYPOINT ["java","-jar","/app.jar"]