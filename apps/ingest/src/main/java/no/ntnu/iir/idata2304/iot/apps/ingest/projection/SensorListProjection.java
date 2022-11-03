package no.ntnu.iir.idata2304.iot.apps.ingest.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorListProjection {
  private Integer id;
  private String place;
  private String room;
}