# Installing sensor nodes

## Prerequisites
In order to follow these instructions you need the following at hand:
- A Raspberry Pi
- A device able to flash your boot media of choice
- A keyboard
- A monitor
- A network connection

## Installation
1. Using software such as [balenaEtcher], flash a copy of the [Raspberry Pi OS][rpi-os] to the boot media of choice. 
   During our tests, the Lite distribution of the Raspberry Pi OS has been used.

2. Insert the boot media to the device and power the device.

3. Follow the installation instructions on screen to set up a keyboard language and user account.

4. Log in to the operating system and set up your network connection.
   
   - If you are using Ethernet, you could already be set assigned an IP address from your local DHCP server.
     You can check the IP address of the network card

   - If you want to use WiFi, use `sudo raspi-config` to configure the **WLAN Country** under **5 Localisation Options**. 
     Connect to your WiFi using `sudo raspi-config` under **1 System Options**.

5. Set up a new user account for running the software.
   
   - TODO: determine whether we want to provide a prompt for this in an install script


[balenaEtcher]: https://www.balena.io/etcher/
[rpi-os]: https://www.raspberrypi.com/software/operating-systems/