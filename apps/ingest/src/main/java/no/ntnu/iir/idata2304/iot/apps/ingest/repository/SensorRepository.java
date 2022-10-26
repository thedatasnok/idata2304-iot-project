package no.ntnu.iir.idata2304.iot.apps.ingest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import no.ntnu.iir.idata2304.iot.apps.ingest.model.Sensor;
import no.ntnu.iir.idata2304.iot.apps.ingest.model.SensorIdentifier;

@RepositoryDefinition(
  domainClass = Sensor.class,
  idClass = SensorIdentifier.class
)
public interface SensorRepository {

  /**
   * Finds all sensors stored in the database.
   * 
   * @return a list of all sensors stored in the database
   */
  List<Sensor> findAll();

  /**
   * Finds a sensor from the database by it's id/primary key.
   * 
   * @param identifier the sensors identifier
   * 
   * @return an optional containing the found sensor, or an empty optional if none were found
   */
  Optional<Sensor> findById(SensorIdentifier identifier);

  /**
   * Saves a sensor to the database.
   * 
   * @param sensor the sensor to save
   * 
   * @return the saved sensor, with all generated/default values
   */
  Sensor save(Sensor sensor);

}