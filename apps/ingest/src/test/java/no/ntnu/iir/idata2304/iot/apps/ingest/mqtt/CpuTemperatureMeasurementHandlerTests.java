package no.ntnu.iir.idata2304.iot.apps.ingest.mqtt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import no.ntnu.iir.idata2304.iot.apps.ingest.event.model.CpuTemperatureMeasurementCreatedEvent;
import no.ntnu.iir.idata2304.iot.apps.ingest.repository.CpuTemperatureMeasurementRepository;

@SpringBootTest
@ActiveProfiles("test")
@RecordApplicationEvents
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CpuTemperatureMeasurementHandlerTests {
  
  @Autowired private CpuTemperatureMeasurementHandler cpuTemperatureMeasurementHandler;
  @Autowired private CpuTemperatureMeasurementRepository repository;
  @Autowired private ApplicationEvents applicationEvents;

  private static final Float MOCK_TEMPERATURE = 32840 / 1000f;

  @Test
  void measurementIsCreatedWhenAMessageIsReceived() {
    var date = new Date();

    var mockMessage = new GenericMessage<>(
      MOCK_TEMPERATURE.toString(), 
      Map.of("mqtt_receivedTopic", "g9hood/datasnok/room/cpu/group09/1")
    );

    this.cpuTemperatureMeasurementHandler.handleMessage(mockMessage);
    var savedMeasurements = this.repository.findAllAfter(date);
    
    assertEquals(1, savedMeasurements.size());

    this.cpuTemperatureMeasurementHandler.handleMessage(mockMessage);
    savedMeasurements = this.repository.findAllAfter(date);

    assertEquals(2, savedMeasurements.size());
  }

  @Test
  void eventPublishedWhenMessageIsReceived() {
    var mockMessage = new GenericMessage<>(
      MOCK_TEMPERATURE.toString(), 
      Map.of("mqtt_receivedTopic", "g9hood/datasnok/room/cpu/group09/1")
    );

    this.cpuTemperatureMeasurementHandler.handleMessage(mockMessage);
    var eventCount = this.applicationEvents.stream(CpuTemperatureMeasurementCreatedEvent.class)
      .count();
    
    assertEquals(1, eventCount);
    
    this.cpuTemperatureMeasurementHandler.handleMessage(mockMessage);
    eventCount = this.applicationEvents.stream(CpuTemperatureMeasurementCreatedEvent.class)
      .count();

    assertEquals(2, eventCount);
  }

}
