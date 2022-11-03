package no.ntnu.iir.idata2304.iot.apps.ingest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import no.ntnu.iir.idata2304.iot.apps.ingest.projection.SensorListProjection;
import no.ntnu.iir.idata2304.iot.apps.ingest.repository.SensorRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sensors")
public class SensorController {
  private final SensorRepository sensorRepository;

  @GetMapping
  public ResponseEntity<List<SensorListProjection>> findAll() {
    return ResponseEntity.ok(this.sensorRepository.findAll());
  }
}
