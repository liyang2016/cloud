#!/usr/bin/env bash

java -Xmx200m -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$1 -jar /app/$2.jar