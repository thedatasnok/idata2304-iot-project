package no.ntnu.iir.idata2304.iot.apps.ingest.event.model;

import no.ntnu.iir.idata2304.iot.apps.ingest.model.CpuTemperatureMeasurement;
import no.ntnu.iir.idata2304.iot.apps.ingest.projection.CpuTemperatureMeasurementProjection;

/**
 * Represents the event of a CPU temperature measurement being created.
 */
public class CpuTemperatureMeasurementCreatedEvent {
  private CpuTemperatureMeasurementProjection measurement;

  public CpuTemperatureMeasurementCreatedEvent(CpuTemperatureMeasurement measurement) {
    // we convert to the projection because this information is converted to JSON.
    // converting to JSON may cause stack overflow errors in the future if we make bidirectional
    // mappings of the relationships.
    this.measurement = new CpuTemperatureMeasurementProjection(
      measurement.getId(),
      measurement.getTemperature(),
      measurement.getSensor().getId(),
      measurement.getSensor().getPlace(),
      measurement.getSensor().getRoom(),
      measurement.getMeasuredAt()
    );
  }

  /**
   * Returns the CPU temperature measurement projection. 
   * 
   * @return the CPU temperature measurement projection
   */
  public CpuTemperatureMeasurementProjection getMeasurement() {
    return this.measurement;
  }

}
