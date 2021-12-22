docker-build:
	mvn clean && mvn package && docker build -t ghcr.io/sidemesh/binance-bot/bot:latest .