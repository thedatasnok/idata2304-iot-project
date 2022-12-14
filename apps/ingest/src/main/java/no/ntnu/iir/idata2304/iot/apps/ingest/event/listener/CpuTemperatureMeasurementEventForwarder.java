package no.ntnu.iir.idata2304.iot.apps.ingest.event.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.ntnu.iir.idata2304.iot.apps.ingest.event.model.CpuTemperatureMeasurementCreatedEvent;

/**
 * Represents an event forwarder for CPU measurement events.
 * Responsible for listening to CPU temperature measurement events 
 * and forwarding events to a list of emitters that are subscribing.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CpuTemperatureMeasurementEventForwarder {
  private List<SseEmitter> emitters = new ArrayList<>();

  /**
   * Handles when a new CPU temperature measurement is created.
   * 
   * @param createdMeasurement the created CPU temperature measurement
   */
  @EventListener(classes = CpuTemperatureMeasurementCreatedEvent.class)
  public void onCpuMeasurementCreated(CpuTemperatureMeasurementCreatedEvent createdMeasurement) {
    try {
      for (SseEmitter emitter : this.emitters) {
        createdMeasurement.getMeasurement().truncateSeconds();
        emitter.send(createdMeasurement.getMeasurement());
      }
    } catch (Exception e) {
      log.error("Failed to forward CPU temperature measurement creation to socket: {}", e.getMessage());
      log.debug("Error forwarding measurement: ", e);
    }
  }

  /**
   * Adds an emitter to the list of emitters to forward creation events to.
   * 
   * @param emitter the emitter to add to the list
   */
  public void addEmitter(SseEmitter emitter) {
    emitter.onError(err -> {
      log.error("Removing errored emitter from forwarded emitters, because: {}", err.getMessage());
      log.debug("Forwarded emitter error: ", err);
      this.emitters.remove(emitter);
    });

    emitter.onTimeout(() -> {
      log.debug("Removing timed out emitter from forwarded emitters");
      this.emitters.remove(emitter);
    });

    emitter.onCompletion(() -> {
      log.debug("Removing completed emitter from forwarded emitters");
      this.emitters.remove(emitter);
    });

    this.emitters.add(emitter);
  }

}
