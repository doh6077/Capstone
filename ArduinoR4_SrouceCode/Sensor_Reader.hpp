#ifndef SENSOR_READER_HEADER
#define SENSOR_READER_HEADER

#include <Arduino.h>
#include <Arduino_JSON.h>
#include <iomanip>
#include "WiFi_Connecter.hpp"
#include "Sensor.hpp"
class SensorReader {
private:
  WiFiConnecter wifiConnecter{};
  String macAddress;
  Sensor sensors[3];
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
  String readData();
  String registerSensor();
};

#endif
