# org.javacream.training.spring.security
Samples using Spring Boot and Spring Security

## Common Security Themes

### Play around with WebGoat 7.1 using docker

* `docker pull webgoat/webgoat-7.1`
* `docker run -p 8080:8080 -d --name webgoat-7.1 -v webgoat-7.1:/tmp webgoat/webgoat-7.1`
* open `http://localhost:8080/WebGoat` and worh through the training labs

### Play around with WebGoat 8 and WebWolf using docker

Warning: as state of today (Juliy 2020) this project is still a work in progress!

* `docker pull webgoat/goatandwolf`
* `docker run -d --name webgoat_webwolf -p 8081:8080 -p 9091:9090 -e TZ=Europe/Amsterdam webgoat/goatandwolf`
* open `http://localhost:8081/WebGoat` `http://localhost:9081/WebWolf` and worh through the training labs
* [detailed instructions](https://github.com/WebGoat/WebGoat)
