#include "R4_MQTT.hpp"

void Mqtt_Publisher::connectToBroker() {
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


void Mqtt_Publisher::publish(float distance) {
  mqttClient.poll();
  mqttClient.beginMessage(topic);
  mqttClient.print(distance);
  mqttClient.endMessage();
}
