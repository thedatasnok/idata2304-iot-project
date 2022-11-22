package no.ntnu.iir.idata2304.iot.apps.sensor.mqtt;

import java.net.InetAddress;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageHandler;

/**
 * Configurtation class for configuring the MQTT client factory with the configured properties.
 */
@Configuration
public class MqttConfig {

  @Value("${sensor.mqtt.broker.ip}")
  private InetAddress brokerAddress;

  @Value("${sensor.mqtt.broker.port}")
  private Integer brokerPort;
  
  @Value("${sensor.mqtt.client.id}")
  private String clientId;
  
  @Value("${sensor.place.id}")
  private String placeId;
  
  @Value("${sensor.room.id}")
  private String roomId;
  
  @Value("${sensor.id}")
  private Integer sensorId;

  @Bean
  public MqttPahoClientFactory mqttClientFactory() {
    DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
    MqttConnectOptions options = new MqttConnectOptions();
    options.setServerURIs(new String[] { this.buildMqttServerUri() });
    factory.setConnectionOptions(options);
    return factory;
  }

  @Bean
  @ServiceActivator(inputChannel = "mqttOutboundChannel")
  public MessageHandler mqttOutbound() {
    var messageHandler = new MqttPahoMessageHandler(this.clientId, this.mqttClientFactory());
    messageHandler.setAsync(true);
    messageHandler.setDefaultTopic(this.buildMqttTopic());
    return messageHandler;
  }

  /**
   * Builds the MQTT server URI from the configured properties.
   * 
   * @return MQTT server URI from the configured properties
   */
  private String buildMqttServerUri() {
    return String.format("tcp://%s:%d", this.brokerAddress.getHostAddress(), this.brokerPort);
  }

  /**
   * Builds the MQTT topic from the configured properties.
   * 
   * @return MQTT topic from the configured properties
   */
  private String buildMqttTopic() {
    return String.format("g9hood/%s/%s/cpu/group09/%d", this.placeId, this.roomId, this.sensorId);
  }
}
