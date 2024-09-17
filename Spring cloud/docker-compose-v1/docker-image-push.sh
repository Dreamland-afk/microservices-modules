#!/bin/bash

TAG_NAME=$1

if [ -z "$TAG_NAME" ]; then
    echo "Tag name is required"
    exit 1
fi

docker push swapnadeepmondal/cards:$TAG_NAME
docker push swapnadeepmondal/loans:$TAG_NAME
docker push swapnadeepmondal/account:$TAG_NAME
docker push swapnadeepmondal/configserver:$TAG_NAME