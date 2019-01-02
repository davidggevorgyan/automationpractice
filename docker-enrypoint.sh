#!/usr/bin/env bash
docker-compose up --force-recreate | grep -q "Zalenium is now ready!"
if [ $? == 0 ]; then
    # run initializing commands
    mvn clean test -Dselenium.host=zalenium
fi

docker stop zalenium