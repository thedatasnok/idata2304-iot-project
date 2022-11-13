#!/bin/bash
cd ~

curl -s https://api.github.com/repos/thedatasnok/idata2304-iot-project/releases/latest \
  | grep "browser_download_url.*sensor" \
  | cut -d : -f 2,3 \
  | tr -d \" \
  | xargs wget --directory-prefix=./ab-sensor/

echo "[Unit]
Description=Antiboom sensor node
After=network.target
StartLimitIntervalSec=0

[Service]
Type=simple
Restart=always
RestartSec=1
User=root
ExecStart=~/ab-sensor/sensor

[Install]
WantedBy=multi-user.target" > /etc/systemd/system/ab-sensor-service.service

read -p "MQTT Broker IP: " MQTT_BROKER_IP
read -p "MQTT Broker Port: " MQTT_BROKER_PORT
read -p "MQTT Client ID: " MQTT_CLIENT_ID
read -p "Sensor Place ID: " SENSOR_PLACE_ID
read -p "Sensor Room ID: " SENSOR_ROOM_ID
read -p "Sensor ID: " SENSOR_ID

echo "export MQTT_BROKER_IP=$MQTT_BROKER_IP" >> ~/.bashrc
echo "export MQTT_BROKER_PORT=$MQTT_BROKER_PORT" >> ~/.bashrc
echo "export MQTT_CLIENT_ID=$MQTT_CLIENT_ID" >> ~/.bashrc
echo "export SENSOR_PLACE_ID=$SENSOR_PLACE_ID" >> ~/.bashrc
echo "export SENSOR_ROOM_ID=$SENSOR_ROOM_ID" >> ~/.bashrc
echo "export SENSOR_ID=$SENSOR_ID" >> ~/.bashrc

source .bashrc

systemctl daemon-reload
systemctl start ab-sensor-service
systemctl enable ab-sensor-service