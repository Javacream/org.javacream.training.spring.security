# org.javacream.training.spring.security
Samples using Spring Boot and Spring Security
## Seminarinformationen Durchführung 13.-14.7.2020 in Leipzig

* Referent: Rainer Sawitzki, eMail: training@rainer-sawitzki.de

* Beispiele
  * https://github.com/Javacream/org.javacream.training.security
    *  Branch davaso-13.7.2020
* Digitales Flipchart
  * https://docs.google.com/presentation/d/1lTgQJZruRASR95a3zCKnpsQJMFKUieYvLobSu1cZmb0/edit?usp=sharing
* Seminarzeiten
  * 8 Unterrichtseinheiten mit jeweils etwa 90 Minuten
  * Mittagspause 45’, Kaffeepausen nach Bedarf
  * Vorschlag: Pausenzeit gesamt etwa 1:00h
  * Zeitplan 
    * Mo.: 9:00-17:00
    * Di.: 9:00-15:00

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
