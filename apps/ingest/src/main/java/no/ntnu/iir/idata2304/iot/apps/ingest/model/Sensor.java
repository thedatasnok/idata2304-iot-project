package no.ntnu.iir.idata2304.iot.apps.ingest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a single sensor node. 
 * Main purpose is to split out information about a sensor from the measurements.
 * Could potentially in the future be used to hold any extra metadata if needed.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@IdClass(SensorIdentifier.class)
@Table(name = Sensor.TABLE_NAME)
public class Sensor {
  
  public static final String TABLE_NAME = "sensor";

  @Id
  @Column(name = "sensor_id")
  private Integer id;

  @Id
  @Column(name = "place")
  private String place;

  @Id
  @Column(name = "room")
  private String room;

  /**
   * Constructs a new Sensor node instance.
   * 
   * @param id the id of the sensor
   * @param place the place where the sensor is located
   * @param room the room where the sensor is located in
   */
  public Sensor(Integer id, String place, String room) {
    this.id = id;
    this.place = place;
    this.room = room;
  }

  /**
   * Returns the sensors identifier. (IdClass)
   * 
   * @return the sensors identifier
   * 
   * @see SensorIdentifier
   */
  public SensorIdentifier getIdentifier() {
    return new SensorIdentifier(this.id, this.place, this.room);
  }
  
}
