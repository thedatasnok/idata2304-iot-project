#!/bin/bash

INSTALL_LOCATION=/usr/local/antiboom
ENVIRONMENT_FILE=$INSTALL_LOCATION/environment

curl -s https://api.github.com/repos/thedatasnok/idata2304-iot-project/releases/latest \
  | grep "browser_download_url.*sensor" \
  | cut -d : -f 2,3 \
  | tr -d \" \
  | xargs wget --directory-prefix=$INSTALL_LOCATION

chmod +x $INSTALL_LOCATION/sensor

read -p "MQTT Broker IP: " MQTT_BROKER_IP
read -p "MQTT Broker Port: " MQTT_BROKER_PORT
read -p "MQTT Client ID: " MQTT_CLIENT_ID
read -p "Sensor Place ID: " SENSOR_PLACE_ID
read -p "Sensor Room ID: " SENSOR_ROOM_ID
read -p "Sensor ID: " SENSOR_ID

# create a file for storing the sensor config/environment variables
touch $ENVIRONMENT_FILE

# store variables in environment
echo "MQTT_BROKER_IP=$MQTT_BROKER_IP" >> $ENVIRONMENT_FILE
echo "MQTT_BROKER_PORT=$MQTT_BROKER_PORT" >> $ENVIRONMENT_FILE
echo "MQTT_CLIENT_ID=$MQTT_CLIENT_ID" >> $ENVIRONMENT_FILE
echo "SENSOR_PLACE_ID=$SENSOR_PLACE_ID" >> $ENVIRONMENT_FILE
echo "SENSOR_ROOM_ID=$SENSOR_ROOM_ID" >> $ENVIRONMENT_FILE
echo "SENSOR_ID=$SENSOR_ID" >> $ENVIRONMENT_FILE

echo "[Unit]
Description=Antiboom sensor node
After=network.target
StartLimitIntervalSec=0

[Service]
EnvironmentFile=$ENVIRONMENT_FILE
Type=simple
Restart=always
RestartSec=1
User=root
ExecStart=$INSTALL_LOCATION/sensor

[Install]
WantedBy=multi-user.target" > /etc/systemd/system/ab-sensor-service.service

systemctl daemon-reload
systemctl start ab-sensor-service
systemctl enable ab-sensor-service