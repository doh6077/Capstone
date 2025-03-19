#ifndef R4_MQTT_HEADER
#define R4_MQTT_HEADER

#include <ArduinoMqttClient.h>
#include <WiFiClient.h>
#include "Sensor_Reader.hpp"

class Mqtt_Publisher {
private:

  WiFiClient wifiClient;
  MqttClient mqttClient{ wifiClient };

  // Set up MQTT broker interface value:
  // Home Local IP: { 192, 168, 40, 21 }
  // ROOM 144A ethernet IP { 10, 14, 3, 8 }
  IPAddress pi_ip{ 192, 168, 40, 21 };
  int pi_broker_port = 1883;
  char* topic = "test/topic";
  bool isConnected = false;

public:
  void connectToBroker();
  void publish(float distance);
};

#endif