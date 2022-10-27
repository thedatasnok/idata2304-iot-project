CREATE TABLE cpu_temperature_measurement (
  measurement_id BIGINT GENERATED ALWAYS AS IDENTITY,
  fk_sensor_id BIGINT NOT NULL,
  fk_sensor_place VARCHAR NOT NULL,
  fk_sensor_room VARCHAR NOT NULL,
  temperature NUMERIC NOT NULL,
  measured_at TIMESTAMP NOT NULL DEFAULT now(),

  FOREIGN KEY 
    (fk_sensor_id, fk_sensor_place, fk_sensor_room)
  REFERENCES sensor
    (sensor_id, place, room),

  PRIMARY KEY(measurement_id)
);
