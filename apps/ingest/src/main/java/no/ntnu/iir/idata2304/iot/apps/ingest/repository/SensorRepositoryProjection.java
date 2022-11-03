package no.ntnu.iir.idata2304.iot.apps.ingest.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorRepositoryProjection {
  private Integer id;
  private String place;
  private String room;
}