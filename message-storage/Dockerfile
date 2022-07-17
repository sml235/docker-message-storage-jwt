FROM maven:3.8-openjdk-11

WORKDIR /message-storage
COPY . .
RUN mvn clean install

CMD ["mvn", "spring-boot:run"]