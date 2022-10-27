package no.ntnu.iir.idata2304.iot.apps.ingest.mqtt;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

@Configuration
public class MqttConfig {

  @Value("${ingest.mqtt.broker.ip}")
  private InetAddress ipAddress;

  @Value("${ingest.mqtt.broker.port}")
  private Integer port;

  @Value("${ingest.mqtt.client.id}")
  private String clientId;

  private static final String TOPIC = "g9hood/+/+/cpu/group09/+";

  private static final int TIMEOUT = 5000;

  @Bean
  public MessageChannel mqttInputChannel() {
    return new DirectChannel();
  }

  @Bean
  public MessageProducer inbound() {
    var adapter = new MqttPahoMessageDrivenChannelAdapter(
        this.buildMqttServerUri(), 
        this.clientId, 
        TOPIC
    );

    adapter.setCompletionTimeout(TIMEOUT);
    adapter.setConverter(new DefaultPahoMessageConverter());
    adapter.setQos(1);
    adapter.setOutputChannel(mqttInputChannel());

    return adapter;
  }

  private String buildMqttServerUri() {
    return String.format("tcp://%s:%d", this.ipAddress.getHostAddress(), this.port);
  }
}
