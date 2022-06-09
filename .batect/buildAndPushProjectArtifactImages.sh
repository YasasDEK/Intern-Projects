  #! /usr/bin/env sh

## Build project artifacts

set -euo pipefail

#TAG=$(git rev-parse HEAD)
TAG="0.0.1-rc1"

DOCKER_REGISTRY_URL_FOR_LOGIN=""
DOCKER_REGISTRY_URL_FOR_PUBLISH=""
DOCKER_REGISTRY_AVAILABLE=0

if [ -z ${DOCKER_REGISTRY:-} ]; then
    echo "Publishing to local docker"
else
    echo "Publishing to Docker registry ${DOCKER_REGISTRY}"
    DOCKER_REGISTRY_URL_FOR_LOGIN="${DOCKER_REGISTRY}"
    DOCKER_REGISTRY_URL_FOR_PUBLISH="${DOCKER_REGISTRY}/"
    DOCKER_REGISTRY_AVAILABLE=1
fi

if [ $DOCKER_REGISTRY_AVAILABLE -eq 1 ]; then
    echo "Using Docker registry Login URL: [${DOCKER_REGISTRY_URL_FOR_LOGIN}], Publish URL [${DOCKER_REGISTRY_URL_FOR_PUBLISH}]"
    echo ${DOCKER_PASSWORD} | docker login ${DOCKER_REGISTRY_URL_FOR_LOGIN} --username ${DOCKER_USER} --password-stdin
else
    echo "Images will not be publish to Docker registry set 'DOCKER_REGISTRY' variable to support publishing."
fi

## API Server
docker build -t ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/release-note-manager-api:latest -f release-note-manager-api/Dockerfile.dev release-note-manager-api
docker tag ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/release-note-manager-api:latest ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/release-note-manager-api:$TAG
if [ $DOCKER_REGISTRY_AVAILABLE -eq 1 ]; then
    docker push ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/release-note-manager-api:latest
    docker push ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/release-note-manager-api:$TAG
fi

## Web App
docker build -t ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/webapp:latest -f webapp/docker/Dockerfile.dev webapp
docker tag ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/webapp:latest ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/webapp:$TAG
if [ $DOCKER_REGISTRY_AVAILABLE -eq 1 ]; then
    docker push ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/webapp:latest
    docker push ${DOCKER_REGISTRY_URL_FOR_PUBLISH}rnm/webapp:$TAG
fi

