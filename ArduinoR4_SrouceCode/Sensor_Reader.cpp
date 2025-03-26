#include "Sensor_Reader.hpp"

void SensorReader::setupSensor() {
  // 1st sensor
  pinMode(trigerPin1, OUTPUT);  // Set Trig as output
  pinMode(echoPin1, INPUT);   // Set Echo as input Serial.begin(9600); // Start serial monitor

  // 2nd sensor
  pinMode(trigerPin2, OUTPUT);
  pinMode(echoPin2, INPUT);

  //3rd sensor
  pinMode(trigerPin3, OUTPUT);
  pinMode(echoPin3, INPUT);
}

float SensorReader::readSensorData1() {
  digitalWrite(trigerPin1, LOW);
  delayMicroseconds(2);
  digitalWrite(trigerPin1, HIGH);
  delayMicroseconds(10);  // Send a 10-microsecond pulse
  digitalWrite(trigerPin1, LOW);
  long duration = pulseIn(echoPin1, HIGH);  // Read the Echo pin response time
  float distance = duration * 0.034 / 2;    // Convert to cm
  delay(500);                               // Measure every 500ms

  //print for debugging:
  Serial.print("Distance1: ");
  Serial.print(distance);
  Serial.println(" cm");

  return distance;
}
float SensorReader::readSensorData2() {
  digitalWrite(trigerPin2, LOW);
  delayMicroseconds(2);
  digitalWrite(trigerPin2, HIGH);
  delayMicroseconds(10);  // Send a 10-microsecond pulse
  digitalWrite(trigerPin2, LOW);
  long duration = pulseIn(echoPin2, HIGH);  // Read the Echo pin response time
  float distance = duration * 0.034 / 2;    // Convert to cm
  delay(500);                               // Measure every 500ms

  //print for debugging:
  Serial.print("Distance2: ");
  Serial.print(distance);
  Serial.println(" cm");

  return distance;
}
float SensorReader::readSensorData3() {
  digitalWrite(trigerPin3, LOW);
  delayMicroseconds(2);
  digitalWrite(trigerPin3, HIGH);
  delayMicroseconds(10);  // Send a 10-microsecond pulse
  digitalWrite(trigerPin3, LOW);
  long duration = pulseIn(echoPin3, HIGH);  // Read the Echo pin response time
  float distance = duration * 0.034 / 2;    // Convert to cm
  delay(500);                               // Measure every 500ms

  //print for debugging:
  Serial.print("Distance3: ");
  Serial.print(distance);
  Serial.println(" cm");

  return distance;
}
String SensorReader::registerSensor() {
    setupSensor();
    JSONVar sensorJSON;
    sensorJSON["macAddress"] = wifiConnecter.getBoardMAC();
    sensorJSON["trigerPin1"] = trigerPin1;
    sensorJSON["echoPin1"] = echoPin1;
    sensorJSON["trigerPin2"] = trigerPin2;
    sensorJSON["echoPin2"] = echoPin2;
    sensorJSON["trigerPin3"] = trigerPin3;
    sensorJSON["echoPin3"] = echoPin3;
    String sensorMetadata = JSON.stringify(sensorJSON);
    return sensorMetadata;
}
