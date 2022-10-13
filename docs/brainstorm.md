# Project scenario brainstorming / ideas

## Sensor/IoT device health

Imagine an IoT device, a Raspberry Pi is an example. 
IoT devices are placed in various environments resulting in differing runtime temperatures for the devices. 
If the device gets too hot it might damage the device causing some cost for the owner of the device. 
The owner of the device wants to monitor the temperatures of the devices CPUs.


The case can for instance be a Raspberry Pi that is used to control the gas price displays at a gas station.


## Room temperature compliance / Greenhouse

A person y is interested in knowing the temperature of a Greenhouse. 
The result of newer research showed that a plant of Copium has the best growing conditions when the temperature is around 19.5 degrees celcius.


## Automatic light onturner

When the light levels in a given environment (a room for instance) goes below x, the light should be turned on.


Plotting the light level in a 2 dimensional graph. 
Marking turning off/on the light on the X-axis.


# Potential project extras

- Inspect publicly available information on the MQTT broker
  - Can we expect the data to be in a specific format? 

- Install script (oneliner) `curl <url> | /bin/bash`
  - If we run on a linux based system - we can set up a service in systemd (automatic startup)

- Web visualizations
  - REST(ish) API
    - Documentation using OpenAPI or equivalent methods

  - Realtime updates (WebSockets/Server Sent Events)

- Set up our own MQTT broker

- Offline buffering on sensor nodes
  - Requires more information in the messages (log-date and value at a bare minimum)

- Database migrations (evolving the data schema with versioned changelogs)

