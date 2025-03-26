#include "Mqtt_Publisher.hpp"

void MqttPublisher::connectToBroker() {
  Serial.print("Attempting to connect to the MQTT broker: ");
  Serial.println(pi_ip);
  while (!mqttClient.connect(pi_ip, pi_broker_port)) {
    Serial.print("MQTT connection failed! Error code = ");
    Serial.println(mqttClient.connectError());
    mqttClient.connect(pi_ip, pi_broker_port);
    Serial.print("Attempting to connect to the MQTT broker: ");
    Serial.println(pi_ip);
  }
  Serial.println("You're connected to the MQTT broker!");
}


void MqttPublisher::publishSensorReading(String sensorReadingData) {
  Serial.print("Reading Data: ");
  Serial.println(sensorReadingData);
  char* topic = "smartwaste/sensor/readingdata";
  mqttClient.poll();
  mqttClient.beginMessage(topic);
  mqttClient.print(sensorReadingData);
  mqttClient.endMessage();
}
void MqttPublisher::publishSensorMetadata(String sensorMetadata) {
  Serial.print("Sensor Data: ");
  Serial.println(sensorMetadata);
  char* topic = "smartwaste/sensor/metadata";
  mqttClient.poll();
  mqttClient.beginMessage(topic);
  mqttClient.print(sensorMetadata);
  mqttClient.endMessage();
}
