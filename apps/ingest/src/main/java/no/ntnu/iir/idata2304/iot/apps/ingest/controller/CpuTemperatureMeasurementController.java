package no.ntnu.iir.idata2304.iot.apps.ingest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import no.ntnu.iir.idata2304.iot.apps.ingest.event.listener.CpuTemperatureMeasurementEventForwarder;
import no.ntnu.iir.idata2304.iot.apps.ingest.projection.CpuTemperatureMeasurementProjection;
import no.ntnu.iir.idata2304.iot.apps.ingest.repository.CpuTemperatureMeasurementRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cpu-temperatures")
public class CpuTemperatureMeasurementController {
  private final CpuTemperatureMeasurementRepository cpuTempRepository;
  private final CpuTemperatureMeasurementEventForwarder measurementForwarder;
  
  // not sure what the effect of a long timeout is, but 30 seconds is too little
  private static final Long SSE_TIMEOUT = 1000L * 60 * 60; // 1 hour
  
  /**
   * GET endpoint for finding all CPU temperature measurements after a given date.
   * 
   * @param after the date to find measurements after
   * 
   * @return a list of all found measurements
   */
  @GetMapping
  public ResponseEntity<List<CpuTemperatureMeasurementProjection>> findAllAfter(
    @RequestParam("after") @DateTimeFormat(iso = ISO.DATE_TIME) Date after
  ) {
    return ResponseEntity.ok(this.cpuTempRepository.findAllAfter(after));
  }

  /**
   * GET endpoint for subscribing to Server Sent Events for CPU temperature measurements.
   * 
   * @return an SSE emitter emitting events for CPU temperature measurements
   */
  @GetMapping("/events")
  public SseEmitter subscribe() {
    var emitter = new SseEmitter(SSE_TIMEOUT);

    this.measurementForwarder.addEmitter(emitter);

    return emitter;
  }

}
