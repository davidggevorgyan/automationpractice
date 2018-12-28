#!/usr/bin/env bash
docker-compose up --force-recreate | grep -q "Zalenium is now ready!"
if [ $? == 0 ]; then
    # run initializing commands
    mvn clean test -Dselenium.remote=true
fi

docker stop zalenium