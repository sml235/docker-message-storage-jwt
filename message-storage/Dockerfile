FROM maven:3.8-openjdk-11

WORKDIR /auth-service
COPY . .
RUN mvn clean install

CMD ["mvn", "spring-boot:run"]