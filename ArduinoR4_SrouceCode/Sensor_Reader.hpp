#ifndef SENSOR_READER_HEADER
#define SENSOR_READER_HEADER

#include <Arduino.h>
#include <Arduino_JSON.h>

#include "WiFi_Connecter.hpp"
class SensorReader {
private:
  WiFiConnecter wifiConnecter{};

  /*
  Each pair of trigerPin and echoPin represents one sensor.
  The int value represents which pin should be wired.
  */
  // 1st sensor
  const int trigerPin1 = 7;
  const int echoPin1 = 8;

  // 2nd sensor
  const int trigerPin2 = 12;
  const int echoPin2 = 13;

  // 3rd sensor
  const int trigerPin3 = 2;
  const int echoPin3 = 4;

public:
  void setupSensor();
  float readSensorData1();
  float readSensorData2();
  float readSensorData3();

  /*
  Send sensor meta data (MAC address of the Arduio the sensor connects to,
  the pin number) under the topic /smartwaste/sensor/metadata/
  On Java server:
  Check the database: if the sensor with the MAC address and pin numbers already exists, do nothing.
  Otherwise, add a new sensor to the database.
  */
  String registerSensor();
};

#endif
