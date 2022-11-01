<br />
<div align="center">
  <a href="#introduction">
    <img src="docs/assets/wordmark.png" width="200">
  </a>

  <p>
    don't let it explode.
    <br />
    <a href="#3-getting-started"><strong>Get started »</strong></a>
</div>

## 1 Introduction

Antiboom is our solution to the Autumn 2022 IDATA2304 Computer Networks project which involves building a prototype of a meaningful IoT application. The application is a Raspberry Pi health monitor which periodically logs processor temperature(s) and displays them in a user-friendly fashion on a web interface. The main idea stems from the potential problem that arises when IoT devices are placed in hot environments, such as inside fuel price signs at gas stations, where the temperatures may exceed what's considered safe for the device. Antiboom helps prevent any potential device damage by providing a real-time graph of minimum, average and maximum temperatures within a set time period, which also helps discover any flaws with the device, such as broken fans or heat sinks.

## 2 Architecture
The project requires a minimum of two nodes programmed by the students, which is achieved and further abstracted into a total of four components in control:
- Sensor nodes
- Ingestion service
- SQL database
- Visualization service

You can read more about the architecture [here](docs/architecture.md).

### 2.1 Technologies used
Since the project consists of multiple components, a mix of technologies have been used.

Keywords: 
[Gradle][gradle], [Java 17][java-17], [Spring Boot][spring-boot], [MQTT][mqtt],
[H2 SQL][h2-sql], [Liquibase][liquibase], [PNPM][pnpm], [TypeScript][typescript], 
[React][react], [Vite][vite], [Tailwind][tailwind-css], [Recharts][recharts]

The project is structured in a [Gradle][gradle] based monorepository. While not applied here, it allows for defining shared modules across applications among other nice to haves. API contracts or MQTT messages are nice candidates for this.


The sensor and ingestion services are [Java 17][java-17] applications built using the [Spring Boot][spring-boot] framework.


The sensor service uses Spring schedule the temperature polling. 
Spring also provides configurable interfaces for integrating with MQTT that can be shared in case of further expansion of the collected sensor data. 


The ingestion service uses Springs [MQTT][mqtt] integration to listen to messages to consume and store in the database. The Spring Data JPA integration is used to communicate with the database. 
It also uses [Liquibase][liquibase] to evolve the data schema in the [H2 database][h2-sql]. It furthermore uses Spring Web for hosting the frontend and an HTTP based API for the frontend to interface with. 

The visualization service is a [React][react] app written in [TypeScript][typescript], using [Tailwind CSS][tailwind-css] to style it. We make use of [Recharts][recharts] for the graph visualization. The visualization service is bundled together with the ingestion service, using [Vite][vite] as the build tool. 

<br />

You can read more about the technologies we used and why [here](docs/technologies.md).

<a href="#introduction">
  <p align="right">
  To top
  </p>
</a>

## 3 Getting started

### 3.1 Setup
...

### 3.2 Usage
...

<br />

<a href="#introduction">
  <p align="right">
  To top
  </p>
</a>

## 4 Methodology
This project has been developed in 1 week long sprints. Each of the sprints are documented [here](docs/sprints/index.md).


TODO: add links to technologies

[gradle]: https://
[java-17]: https://
[spring-boot]: https://
[mqtt]: https://
[h2-sql]: https://
[liquibase]: https://
[pnpm]: https://
[typescript]: https://
[react]: https://
[vite]: https://
[tailwind-css]: https://
[recharts]: https://