#!/usr/bin/env bash

java -Xmx200m -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app/registry.jar

