FROM openjdk:11.0.13-jre-buster
WORKDIR /binance-bot
ADD target/binance-bot-0.0.1.jar binance-bot-0.0.1.jar
EXPOSE 8080
CMD ["java","-server", "-jar","binance-bot-0.0.1.jar"]
