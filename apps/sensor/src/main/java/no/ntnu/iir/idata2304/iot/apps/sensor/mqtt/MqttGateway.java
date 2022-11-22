package no.ntnu.iir.idata2304.iot.apps.sensor.mqtt;

import org.springframework.integration.annotation.MessagingGateway;

/**
 * Represents the messaging gateway for sending data to the MQTT.
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

  void sendToMqtt(byte[] data);

}
