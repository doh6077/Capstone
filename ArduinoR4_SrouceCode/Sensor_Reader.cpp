#include "Sensor_Reader.hpp"

void SensorReader::setupSensor() {
  // 1st sensor
  pinMode(trigerPin1, OUTPUT);  // Set Trig as output
  pinMode(echoPin1, INPUT);     // Set Echo as input Serial.begin(9600); // Start serial monitor

  // 2nd sensor
  pinMode(trigerPin2, OUTPUT);
  pinMode(echoPin2, INPUT);

  //3rd sensor
  pinMode(trigerPin3, OUTPUT);
  pinMode(echoPin3, INPUT);
}

String SensorReader::readData() {
  JSONVar sensorsReading;
  for (int i = 0; i < 3; i++) {
    JSONVar oneSensorReading;
    digitalWrite(sensors[i].getTrigerPin(), LOW);
    delayMicroseconds(2);
    digitalWrite(sensors[i].getTrigerPin(), HIGH);
    delayMicroseconds(10);
    digitalWrite(sensors[i].getTrigerPin(), LOW);
    long duration = pulseIn(sensors[i].getEchoPin(), HIGH);
    float distance = duration * 0.034 / 2;  // Convert to cm
    char distanceStr[10];
    snprintf(distanceStr, sizeof(distanceStr), "%.2f", distance); // keep 2 digits after decimal
    oneSensorReading["macAddress"] = macAddress;
    oneSensorReading["trigerPin"] = sensors[i].getTrigerPin();
    oneSensorReading["echoPin"] = sensors[i].getEchoPin();
    oneSensorReading["distance"] = distanceStr;
    sensorsReading[i] = oneSensorReading;
  }
  String sensorReadingJSON = JSON.stringify(sensorsReading);
  return sensorReadingJSON;
}

String SensorReader::registerSensor() {
  setupSensor();
  JSONVar sensorJSON;
  macAddress = wifiConnecter.getBoardMAC();
  sensorJSON["macAddress"] = macAddress;
  sensorJSON["trigerPin1"] = trigerPin1;
  sensorJSON["echoPin1"] = echoPin1;
  sensorJSON["trigerPin2"] = trigerPin2;
  sensorJSON["echoPin2"] = echoPin2;
  sensorJSON["trigerPin3"] = trigerPin3;
  sensorJSON["echoPin3"] = echoPin3;
  sensors[0] = Sensor{ macAddress, trigerPin1, echoPin1 };
  sensors[1] = Sensor{ macAddress, trigerPin2, echoPin2 };
  sensors[2] = Sensor{ macAddress, trigerPin3, echoPin3 };
  String sensorMetadata = JSON.stringify(sensorJSON);
  return sensorMetadata;
}
