FROM mariadb:latest

RUN apt-get update && apt-get install -y wget

RUN "123" | mysql -u root -e "CREATE DATABASE aaaaaaaa"