FROM openjdk:18.0.2
WORKDIR /BackEndRestERP
COPY . .

RUN mvn clean install
CMD spring-boot:run