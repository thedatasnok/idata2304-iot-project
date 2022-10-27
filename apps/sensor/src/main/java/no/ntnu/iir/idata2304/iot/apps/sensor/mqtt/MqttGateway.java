package no.ntnu.iir.idata2304.iot.apps.sensor.mqtt;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

  void sendToMqtt(byte[] data);

}
