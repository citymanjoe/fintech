FROM openjdk:17
WORKDIR /app
COPY ./target/fintech.jar  /app
EXPOSE 8080
CMD ["java", "-jar", "fintech.jar" ]