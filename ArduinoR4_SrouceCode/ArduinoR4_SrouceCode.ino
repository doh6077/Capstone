#include "R4_Wifi.hpp"
#include "R4_MQTT.hpp"


WiFiConnecter wiFiConnecter;
Mqtt_Publisher mqttPublisher;
Sensor_Reader sensorReader{};

void setup() {
  //Initialize serial and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
    ;  // wait for serial port to connect. Needed for native USB port only
  }

  // check for the WiFi module, firmware version, and connect:
  wiFiConnecter.checkWifiModule();
  wiFiConnecter.checkFirmwareVersion();
  wiFiConnecter.connectToWifi();

  // connect Arduino board to MQTT broker
  mqttPublisher.connectToBroker();

  // set up sensor
  sensorReader.setupSensor();

}

void loop() {
  
  float distance = sensorReader.readSensorData();
  mqttPublisher.publish(distance);

}
