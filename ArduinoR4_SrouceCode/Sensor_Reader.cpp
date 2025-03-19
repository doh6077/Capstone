#include "Sensor_Reader.hpp"

void Sensor_Reader::setupSensor() {
  pinMode(trigPin, OUTPUT); // Set Trig as output 
  pinMode(echoPin, INPUT);  // Set Echo as input Serial.begin(9600); // Start serial monitor 
  pinMode(trigPin, OUTPUT); // Set Trig as output 
  pinMode(echoPin, INPUT); // Set Echo as input 
}

float Sensor_Reader::readSensorData() {
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);  // Send a 10-microsecond pulse
  digitalWrite(trigPin, LOW);
  long duration = pulseIn(echoPin, HIGH);  // Read the Echo pin response time
  float distance = duration * 0.034 / 2;   // Convert to cm
  delay(500);                              // Measure every 500ms

  //print for debugging:
  Serial.print("Distance: ");
  Serial.print(distance);
  Serial.println(" cm");

  return distance;
}