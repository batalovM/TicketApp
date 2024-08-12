FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /target/TicketApp-1.0-SNAPSHOT.jar /app/ticket.jar
COPY /src/main/resources/tickets.json /app/tickets.json
ENTRYPOINT ["java", "-jar", "ticket.jar"]
