package no.ntnu.iir.idata2304.iot.apps.ingest.mqtt;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.ntnu.iir.idata2304.iot.apps.ingest.model.CpuTemperatureMeasurement;
import no.ntnu.iir.idata2304.iot.apps.ingest.model.Sensor;
import no.ntnu.iir.idata2304.iot.apps.ingest.repository.CpuTemperatureMeasurementRepository;
import no.ntnu.iir.idata2304.iot.apps.ingest.repository.SensorRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class CpuTemperatureMeasurementHandler implements MessageHandler {
  private final SensorRepository sensorRepository;
  private final CpuTemperatureMeasurementRepository cpuTempMeasurementRepository;
  
  private static final String MQTT_TOPIC_HEADER = "mqtt_receivedTopic";

  // Indexes for the different segments in the MQTT topic
  private static final int PLACE_ID_SEGMENT = 1;
  private static final int ROOM_ID_SEGMENT = 2;
  private static final int SENSOR_ID_SEGMENT = 5;

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {
    String messageTopic = (String) message.getHeaders().get(MQTT_TOPIC_HEADER);

    // we can't throw here without having spring drop the mqtt connection
    // similar to a guard condition, without a thrown exception
    if (messageTopic != null) {
      this.processMessage(message, messageTopic);
    } else {
      log.warn("Received a message without a MQTT topic header, ignoring...");
    }
  }

  /**
   * Processes a message with a MQTT topic.
   * 
   * @param message the message to process
   * @param messageTopic the topic of the MQTT message to process
   */
  private void processMessage(Message<?> message, String messageTopic) {
    var loggingSensor = this.extractSensorDetailsFromTopic(messageTopic);
    var existingSensor = this.sensorRepository.findById(loggingSensor.getIdentifier());

    if (existingSensor.isEmpty()) {
      loggingSensor = this.sensorRepository.save(loggingSensor);
    } else {
      loggingSensor = existingSensor.get();
    }

    var measurement = new CpuTemperatureMeasurement(
      loggingSensor, 
      Float.parseFloat((String) message.getPayload())
    );

    this.cpuTempMeasurementRepository.save(measurement);
    // TODO: emit application event that will be piped through to subscribers
  }

  /**
   * Extracts sensor details from a MQTT topic.
   * 
   * @param topic the topic to extract sensor details from
   * 
   * @return a sensor instance with the details from the topic
   */
  private Sensor extractSensorDetailsFromTopic(String topic) {
    String[] topicSegments = topic.split("/");
    if (topicSegments.length != 6) throw new IllegalArgumentException("Cannot extract sensor data from an illegal topic");

    return new Sensor(
      Integer.parseInt(topicSegments[SENSOR_ID_SEGMENT]),
      topicSegments[PLACE_ID_SEGMENT],
      topicSegments[ROOM_ID_SEGMENT]
    );
  }

  // NOTE: the name of this bean has to be different from the wrapping class' name
  @Bean
  @ServiceActivator(inputChannel = "mqttInputChannel")
  public MessageHandler cpuTempMessageHandler() { 
    return this;
  }

}
