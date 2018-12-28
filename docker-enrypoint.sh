#!/usr/bin/env bash
docker-compose up --force-recreate | grep -q "Zalenium is now ready!"
if [ $? == 0 ]; then
    # run initializing commands
    mvn install test -Dselenium.remote=true
fi

mvn clean
docker stop zalenium
docker rm zalenium