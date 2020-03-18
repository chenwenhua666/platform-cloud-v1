#!/usr/bin/env bash
mvn package

docker build -t platform-server-foundation .