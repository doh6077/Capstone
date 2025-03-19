#ifndef R4_WIFI_HEADER
#define R4_WIFI_HEADER

#include <WiFiS3.h>

class WiFiConnecter {
private:
  char ssid[30] = "Pixel_3014";  // network SSID
  char pass[30] = "hsyhsy000";   // network password
  int status = WL_IDLE_STATUS;

public:
  void checkWifiModule();
  void checkFirmwareVersion();
  void connectToWifi();
  void printWifiData();
  void printCurrentNet();
  void printMacAddress(byte mac[]);
};

#endif