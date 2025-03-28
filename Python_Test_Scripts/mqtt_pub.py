import paho.mqtt.client as mqtt
import json

BROKER = "localhost"  # broker ip
PORT = 1883  # broker port
# TOPIC = "smartwaste/sensor/metadata"
TOPIC = "smartwaste/sensor/reading-data"
MESSAGE = {
    "macAddress":"34:b7:da:5d:80:0c",
    "trigerPin1":7,
    "echoPin1":8,
    "trigerPin2":12,
    "echoPin2":4,
    "trigerPin3":2,
    "echoPin3":13
}

json_payload = json.dumps(MESSAGE)
client = mqtt.Client()
# Connect to the broker
client.connect(BROKER, PORT, 60)
# Publish a message
client.publish(TOPIC, json_payload)
print(f"Published '{MESSAGE}' to topic '{TOPIC}'")

client.disconnect()