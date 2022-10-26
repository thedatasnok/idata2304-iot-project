package no.ntnu.iir.idata2304.iot.apps.ingest.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Identity class for sensors.
 * Represents the composite primary key for a sensor.
 * Is not an entity and cannot be persisted directly.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SensorIdentifier implements Serializable {
  private Integer id;
  private String place;
  private String room;

  /**
   * Constructs an identifier for a Sensor.
   * 
   * @param id the id of the sensor
   * @param place the place where the sensor is located
   * @param room the room where the sensor is located in
   */
  public SensorIdentifier(Integer id, String place, String room) {
    this.id = id; 
    this.place = place;
    this.room = room;
  }
}