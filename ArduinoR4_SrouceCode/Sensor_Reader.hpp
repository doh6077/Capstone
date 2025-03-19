#ifndef SENSOR_READER_HEADER
#define SENSOR_READER_HEADER

#include <Arduino.h>

class Sensor_Reader {
private:
  const int trigPin = 7;
  const int echoPin = 8;

public:
  void setupSensor();
  float readSensorData();
};

#endif
