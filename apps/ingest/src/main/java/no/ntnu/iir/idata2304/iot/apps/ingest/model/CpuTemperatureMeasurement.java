package no.ntnu.iir.idata2304.iot.apps.ingest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a measurement implementation for CPU temperatures.
 * 
 * @see Measurement See Measurement for details on derived functionality
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = CpuTemperatureMeasurement.TABLE_NAME)
public class CpuTemperatureMeasurement extends Measurement {
  
  public static final String TABLE_NAME = "cpu_temperature_measurement";

  @Column(name = "temperature")
  private Float temperature;

  /**
   * Creates a new CPU temperature measurement.
   * 
   * @param sensor the sensor reporting the measurement
   * @param temperature the measured temperature by the sensor
   */
  public CpuTemperatureMeasurement(Sensor sensor, Float temperature) {
    this.setSensor(sensor);
    this.temperature = temperature;
  }

}
