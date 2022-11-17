export interface CpuTemperatureMeasurementProjection {
  id: number;
  temperature: number;
  sensorId: number;
  sensorPlace: string;
  sensorRoom: string;
  measuredAt: number;
}

export interface SensorListProjection {
  id: number;
  place: string;
  room: string;
}

export interface SensorListDetails extends SensorListProjection {
  lastMeasuredTemperature?: number;
  lastMeasuredAt?: number;
}