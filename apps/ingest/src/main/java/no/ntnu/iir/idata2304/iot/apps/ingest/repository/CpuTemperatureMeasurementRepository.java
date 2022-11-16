package no.ntnu.iir.idata2304.iot.apps.ingest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import no.ntnu.iir.idata2304.iot.apps.ingest.model.CpuTemperatureMeasurement;
import no.ntnu.iir.idata2304.iot.apps.ingest.projection.CpuTemperatureMeasurementProjection;

// Repository definition may be removed in favor of a 
// custom implementation class in the impl subpackage
@RepositoryDefinition(
  domainClass = CpuTemperatureMeasurement.class,
  idClass = Long.class
)
public interface CpuTemperatureMeasurementRepository {

  /**
   * Finds all CPU temperature measurements after a given date.
   * 
   * @param after the date to query for measurements after
   * 
   * @return all CPU measurements that satisfies the after criteria
   */
  @Query("""
    SELECT new no.ntnu.iir.idata2304.iot.apps.ingest.projection.CpuTemperatureMeasurementProjection(
      measurement.id,
      measurement.temperature,
      measurement.sensor.id,
      measurement.sensor.place,
      measurement.sensor.room,
      function('DATE_TRUNC', 'second', measurement.measuredAt)
    )
    FROM CpuTemperatureMeasurement measurement
    WHERE measurement.measuredAt > :after
  """)
  List<CpuTemperatureMeasurementProjection> findAllAfter(@Param("after") Date after);

  /**
   * Saves a CPU temperature measurement to the database.
   * 
   * @return the saved CPU temperature measurement, with all generated/default values
   */
  CpuTemperatureMeasurement save(CpuTemperatureMeasurement measurement);

}
