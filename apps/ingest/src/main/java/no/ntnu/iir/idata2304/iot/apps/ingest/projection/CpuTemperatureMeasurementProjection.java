package no.ntnu.iir.idata2304.iot.apps.ingest.projection;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CpuTemperatureMeasurementProjection {
  private Long id;
  private Float temperature;
  private Integer sensorId;
  private String sensorPlace;
  private String sensorRoom;
  private Date measuredAt;
}
