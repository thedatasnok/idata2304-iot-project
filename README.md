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
In order to set up or install the software you will need the following: 
- An ARM64 sensor node runnning a debian based operating system
- A computer with Java 17 (LTS) installed

### 3.1.1 Installing sensor nodes
This step requires you to have an ARM64 based computer with running a Debian based operating system. 
During our testing, we have run the 64-bit version of Raspberry Pi OS Lite.

1. Log in as or elevate your shell to the root user.

    `sudo su`

2. Run the following to invoke the install script: 

    `bash <(curl -s https://raw.githubusercontent.com/thedatasnok/idata2304-iot-project/main/tools/install-sensor.sh)`

3. Fill out details about the MQTT connection

    **Note:** the client needs to be unique per client
  
### 3.1.2 Installing the ingest/visualization node
This step can be run on any computer compatible with Java 17, installing Java is a prerequisite. 
For instance, on a debian based system, it can be installed running: `apt install openjdk-17-jre-headless`

1. Download the latest version of ingest from [GitHub][release-latest]

2. Set up environment variables for the service, an exhaustive list of required variables:

    `DB_USERNAME` the database username to use
    
    `DB_PASSWORD` the database password to use
    
    `MQTT_BROKER_IP` the MQTT brokers IP address
    
    `MQTT_BROKER_PORT` the MQTT brokers port
    
    `MQTT_CLIENT_ID` the unique client id for this application
    

3. Run the application `java -jar ingest-1.0.0.jar`


### 3.2 Usage

Once the ingest/visualization node is set up as described in [3.1.2](#312-installing-the-ingestvisualization-node), you can open up the user interface by visiting: http://localhost:8080/ in your browser.


The sensor node is meant to run in the background unattended, but in case of errors you can control the service using `systemctl`. 


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

[release-latest]: https://github.com/thedatasnok/idata2304-iot-project/releases/latest