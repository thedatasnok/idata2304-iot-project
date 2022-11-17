package no.ntnu.iir.idata2304.iot.apps.ingest.projection;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

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

  /**
   * Returns the measured at date as a unix timestamp.
   * 
   * @return the measured at date as a unix timestamp
   */
  public Long getMeasuredAt() {
    return this.measuredAt.getTime();
  }

  /**
   * Truncates the seconds from the measured at date.
   */
  public void truncateSeconds() {
    this.measuredAt = DateUtils.truncate(this.measuredAt, Calendar.SECOND);
  }
}
