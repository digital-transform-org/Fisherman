CREATE TABLE environmental_observation (
  id IDENTITY PRIMARY KEY,
  timestamp TIMESTAMP,
  air_temperature DOUBLE,
  water_temperature DOUBLE,
  air_pressure DOUBLE,
  water_level DOUBLE,
  wind_speed DOUBLE,
  wind_direction VARCHAR(10),
  cloud_cover DOUBLE,
  precipitation DOUBLE,
  moon_phase VARCHAR(50)
);
