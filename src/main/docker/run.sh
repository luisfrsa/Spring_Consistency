#!/usr/bin/env bash
rm -rf ./target/
cp -r ../../../target ./
#docker build -t sd_concurrency . //will build an image
docker-compose up --build -d