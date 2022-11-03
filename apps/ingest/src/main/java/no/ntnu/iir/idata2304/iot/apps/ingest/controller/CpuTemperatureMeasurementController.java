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

import lombok.RequiredArgsConstructor;
import no.ntnu.iir.idata2304.iot.apps.ingest.projection.CpuTemperatureMeasurementProjection;
import no.ntnu.iir.idata2304.iot.apps.ingest.repository.CpuTemperatureMeasurementRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cpu-temperatures")
public class CpuTemperatureMeasurementController {
  private final CpuTemperatureMeasurementRepository cpuTempRepository;
  
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

}
