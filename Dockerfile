FROM bellsoft/liberica-openjdk-alpine:25.0.1-cds

RUN apk add curl jq

WORKDIR /home/selenium-docker

ADD target/docker-resources     ./
ADD runner.sh                   runner.sh

ENTRYPOINT sh runner.sh

# ENTRYPOINT java -cp "libs/*" \
#          -Dselenium.grid.enabled=true \
#          -Dselenium.grid.hubHost=${HUB_HOST} \
#          -Dbrowser=${BROWSER} \
#          org.testng.TestNG \
#          -threadcount ${THREAD_COUNT} \
#          test-suites/${TEST_SUITE}