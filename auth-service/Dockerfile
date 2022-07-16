FROM maven:3.8-openjdk-11

WORKDIR /auth-service
COPY . .
RUN mvn clean install

EXPOSE 8083

CMD ["mvn", "spring-boot:run"]