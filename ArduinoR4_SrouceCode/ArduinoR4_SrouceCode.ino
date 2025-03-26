#include "WiFi_Connecter.hpp"
#include "Mqtt_Publisher.hpp"


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

  // setup and register sensor
  String sensorMetadata = sensorReader.registerSensor();
  mqttPublisher.publishSensorMetadata(sensorMetadata);
}

void loop() {

  // float distance = sensorReader.readSensorData1();
  // mqttPublisher.publishSensorReading(distance);
  // float distance2 = sensorReader.readSensorData2();
  // mqttPublisher.publishSensorReading(distance2);
  // float distance3 = sensorReader.readSensorData3();
  // mqttPublisher.publishSensorReading(distance3);
  delay(10000);
}
