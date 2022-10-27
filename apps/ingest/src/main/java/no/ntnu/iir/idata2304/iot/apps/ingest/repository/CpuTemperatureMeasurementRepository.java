package no.ntnu.iir.idata2304.iot.apps.ingest.repository;

import org.springframework.data.repository.RepositoryDefinition;

import no.ntnu.iir.idata2304.iot.apps.ingest.model.CpuTemperatureMeasurement;

// Repository definition may be removed in favor of a 
// custom implementation class in the impl subpackage
@RepositoryDefinition(
  domainClass = CpuTemperatureMeasurement.class,
  idClass = Long.class
)
public interface CpuTemperatureMeasurementRepository {

  /**
   * Saves a CPU temperature measurement to the database.
   * 
   * @return the saved CPU temperature measurement, with all generated/default values
   */
  CpuTemperatureMeasurement save(CpuTemperatureMeasurement measurement);

}
