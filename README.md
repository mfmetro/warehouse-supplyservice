# Warehouse System - SupplyService
External service to be used in the _CODE University Warehouse System_ project to simulate incoming supply deliveries. After startup, the SupplyService generates random supplies in random time intervals.

## Running the Service
In order to start up the SupplyService, you need to checkout this repository, build the application, and build the docker image.

```bash
# checkout repository
$ git clone https://github.com/mfmetro/warehouse-supplyservice.git

# build the application
$ cd warehouse-supplyservice
$ mvn package

# build the Docker image
docker build . -t="metro/supplyservice"
```

You are then able to start the `metro/supplyservice` image.

```bash
# start the image
$ docker run -d -p 8080:8080 metro/supplyservice

# use docker ps to find out your container's id
$ docker ps
CONTAINER ID        IMAGE                COMMAND                  CREATED             STATUS              PORTS                     NAMES
1a06b1c64d3e        metro/supplyservice   "/bin/sh -c 'java -j…"   2 minutes ago       Up 2 minutes        0.0.0.0:8081->8081/tcp   eager_davinci

# show logs (you will see that some articles and supply deliveries already have been created)
$ docker logs 1a06
```

As soon as the container is running, you can access the Swagger UI for the SupplyService via `http://localhost:8081/swagger-ui.html` in your browser.

```bash
# stop container
$ docker stop 1a06
```
