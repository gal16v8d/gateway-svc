# Getting Started

### Pre-requisites

* Maven 3
* Java 17

### Docker Image

- In the project dir, build using the command:

```bash
docker build -t gsdd-gateway-svc .
```

- Run the docker image as:

```bash
docker run -d -p 8762:8762 gsdd-gateway-svc
```

### Notes

This service uses [eureka-server](https://github.com/gal16v8d/eureka-server) for communicate to other services, so assuming that we have eureka-server running
and also [fx-course-app](https://github.com/gal16v8d/fx-course-app) running, then you can see this service working:

- http://localhost:8762/courses/
