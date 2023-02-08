FROM adoptopenjdk:11-jre-hotspot

MAINTAINER roman.rudi@outlook.com

WORKDIR /app

COPY target/book-store-0.0.1-SNAPSHOT.jar ./application.jar

EXPOSE 8080

CMD ["java", "-jar", "./application.jar"]


