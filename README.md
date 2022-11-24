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

This is an educational project in the course IDATA2304 Computer Networks. 

## Abstract
There is a growth of technological applications being connected to the internet. These applications are member of numerous industries, that may require the appliances responsible for said applications to run under challenging environmental circumstances. Information screens and fuel price signs are two examples of where such appliances are used. To ensure longevity of the appliances installed used to run said applications, we need to be aware of their working conditions, and be warned if these conditions worsen to a state that could be of potential harm to either the appliance itself or introduce hazards to personnel utilizing the technology. In this project, we have created a prototype system monitoring system health for appliances. The system measures the CPU temperature of devices, and is capable of showing a real-time graph with updates as measurements are processed. It also offers an optional Discord integration for notifying by message if a sensor measures a temperature exceeding a threshold. The system can be further expanded upon in order to support different kinds of measurements, such as the amount of processing power being used or environmental conditions to help describing the working conditions further. 


## 1 Introduction

Antiboom is our solution to the Autumn 2022 IDATA2304 Computer Networks project which involves building a prototype of a meaningful IoT application. The application is a Raspberry Pi health monitor which periodically logs processor temperature(s) and displays them in a user-friendly fashion on a web interface. The main idea stems from the potential problem that arises when IoT devices are placed in hot environments, such as inside fuel price signs at gas stations, where the temperatures may exceed what's considered safe for the device. Antiboom helps prevent any potential device damage by providing a real-time graph of minimum, average and maximum temperatures within a set time period, which also helps discover any flaws with the device, such as broken fans or heat sinks.

## 2 Technologies

A range of protocols are applied in this project, with the two main building blocks being MQTT and HTTP.

MQTT is a lightweight publish-subscribe messaging protocol for Internet of Things applications ([Wikipedia 2022][wikipedia-mqtt]). MQTT is an abbreviation for the previously called Message Queuing Telemetry Transport. Being one of the project's required protocols, it is responsible for publishing the temperature data of a sensor node to a defined topic on a broker, as well as subscribing to said topic in order to enable data reception. MQTT is an application layer protocol that utilizes a TCP connection to a broker to send or receive messages. 

HTTP, short for Hypertext Transfer Protocol, is a fundamental client-server protocol for data exchange on the Web. It can be used to, for instance, fetch resources such as HTML documents. HTTP is also, like MQTT, an application layer protocol making use of a TCP connections to exchange data. The HTTP protocol specifies a set of verbs, or methods, that help differentiate the purpose of a request. In this project, we take advantage of two of these methods: GET and POST. GET requests typically indicate that the requesting client wants to fetch a resource from a HTTP server. POST requests, on the other hand, typically indicates that the client wants to upload a new entity to a resource ([MDN 2022][mdn-http]). 

In cases where extra metadata is useful to narrow or guide the HTTP server as to what the client is looking for, query parameters can be used. Query parameters are key-value pairs of strings encoded as query strings in the HTTP requests URL. These parameters are appended at the end of the request URL. In this project, these parameters are used to give the server a parameter that we can use to limit the response to what is beneficial to the user ([Wikipedia 2022][wikipedia-query-strings]). 

Data being uploaded to a HTTP server is typically encoded using a predetermined format in the requests body. The kind of encoding used varies and is defined in the request and response `Content-Type` headers ([MDN 2022][mdn-http-post]). In this project we have decided to utilize JSON encoding. JSON is short for JavaScript Object Notation and the encoding format stems from the representation of objects in the JavaScript programming language. JavaScript is a scripting language originally made for web pages ([MDN 2022][mdn-javascript]). It allows for more dynamic behaviour than what is achievable using plain HTML and CSS. 

Client-side web applications can be built in numerous ways. Our application is built with TypeScript, another programming language built on top of JavaScript. To represent our user interface in a declarative manner, we use React as a framework. The source code is a mix of TypeScript and HTML markup (tsx), grouped into reusable bits where applicable. TypeScript code in React can be compiled to JavaScript equivalents and furthermore bundled as HTML and JavaScript files that can be served on a HTTP server. The client-side application makes use of the Fetch Browser API to fetch JSON data from the ingestion service. Since JSON objects are how objects are represented in JavaScript, we do not have to serialize our responses in the client application.

HTTP also has support persistent connections. These persistent connections may be used for event-like data transfer, using a technique called server-sent events. The technique is similar to MQTT, where subscribing clients open connections to a HTTP server and receive events in a way determined by the HTTP server. In browsers, there is an API named EventSource that can help interacting with these server-sent events. 


<a href="#introduction">
  <p align="right">
  To top
  </p>
</a>


## 3 Methodology
Antiboom has been developed in 1 week long sprints, with each of the sprints documented [here](docs/sprints/index.md). This was done both to fullfill one of the project requirements (use of agile work methodology), but also because we have had positive former experiences with this type of workflow and believed it would work just as well for this assignment.

Starting the week the assignment was handed out, our group used every tuesday to finalize a sprint and start a new one. This included discussing and writing a short report about the sprint, as well as planning, defining and assigning tasks as issues on our [GitHub issue board](https://github.com/users/thedatasnok/projects/2/). This usually went by smoothly as we had no problems distributing the issues according to our interests while keeping the workload mostly fair.

Each thursday, we used the available classroom hours to meet in person and work on the project together. We did this to clear up any potential confusion about the assigned work, as well as help each other with mostly, but not only, programming-specific things.


<a href="#introduction">
  <p align="right">
  To top
  </p>
</a>


## 4 Results

### 4.1 Architecture
The project requires a minimum of two nodes programmed by the students, which is achieved and further abstracted into a total of four components in control:
- Sensor nodes
- Ingestion service
- SQL database
- Visualization service


### 4.2 Installation & Usage
In order to set up or install the software you will need the following: 
- An ARM64 sensor node runnning a debian based operating system
- A computer with Java 17 (LTS) installed

Both devices will need an internet connection.


#### 4.2.1 Installing sensor nodes
This step requires you to have an ARM64 based computer with running a Debian based operating system. 
During our testing, we have run the 64-bit version of Raspberry Pi OS Lite.

1. Log in as or elevate your shell to the root user.

    `sudo su`

2. Run the following to invoke the install script: 

    `bash <(curl -s https://raw.githubusercontent.com/thedatasnok/idata2304-iot-project/main/tools/install-sensor.sh)`

3. Fill out details about the MQTT connection

    **Note:** the client ID needs to be unique per client


#### 4.2.2 Installing the ingest/visualization node
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


#### 4.2.3 Usage
Once the ingest/visualization node is set up as described in [3.1.2](#312-installing-the-ingestvisualization-node), you can open up the user interface by visiting: http://localhost:8080/ in your browser.


The sensor node is meant to run in the background unattended, but in case of errors you can control the service using `systemctl`. 


<a href="#introduction">
  <p align="right">
  To top
  </p>
</a>


## 5 Discussion


<a href="#introduction">
  <p align="right">
  To top
  </p>
</a>


## Conclusion and future work


<br />

<a href="#introduction">
  <p align="right">
  To top
  </p>
</a>


## x References
- Wikipedia. *MQTT*. 24th of November 2022. [link][wikipedia-mqtt]
- Mozilla MDN. *HTTP*. 24th of November 2022. [link][mdn-http]
- Wikipedia. *Query string*. 24th of November 2022. [link][wikipedia-query-string]
- Mozilla MDN. *POST - HTTP*. 24th of November 2022. [link][mdn-http-post]
- Mozilla MDN. *JavaScript*. 24th of November 2022. [link][mdn-javascript]

[wikipedia-mqtt]: https://en.wikipedia.org/wiki/MQTT
[mdn-http]: https://developer.mozilla.org/en-US/docs/Web/HTTP
[wikipedia-query-strings]: https://en.wikipedia.org/wiki/Query_string
[mdn-http-post]: https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST
[mdn-javascript]: https://developer.mozilla.org/en-US/docs/Web/JavaScript

TODO: add links to technologies

[mqtt]: https://
[tcp]: https://
[http]: https://
[json]: https://

[gradle]: https://
[java-17]: https://
[spring-boot]: https://
[h2-sql]: https://
[liquibase]: https://
[pnpm]: https://
[typescript]: https://
[react]: https://
[vite]: https://
[tailwind-css]: https://
[recharts]: https://

[release-latest]: https://github.com/thedatasnok/idata2304-iot-project/releases/latest