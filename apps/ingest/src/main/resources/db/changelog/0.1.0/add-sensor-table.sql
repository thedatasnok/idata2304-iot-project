CREATE TABLE sensor (
  sensor_id BIGINT NOT NULL,
  place VARCHAR NOT NULL,
  room VARCHAR NOT NULL,

  PRIMARY KEY (sensor_id, place, room)
);
