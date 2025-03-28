#ifndef SENSOR_HEADER
#define SENSOR_HEADER

#include <Arduino.h>

class Sensor {
private:
  String macAddress;
  int trigerPin;
  int echoPin;
public:
  Sensor() {}
  Sensor(String macAddress, int trigerPin, int echoPin)
    : macAddress(macAddress), trigerPin(trigerPin), echoPin(echoPin) {}
  String getMacAddress() const {return macAddress;}
  int getTrigerPin() const {return trigerPin;}
  int getEchoPin() const {return echoPin;}
};

#endif