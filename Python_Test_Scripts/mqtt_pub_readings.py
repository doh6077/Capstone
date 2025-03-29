import paho.mqtt.client as mqtt
import json
import time
import random

BROKER = "localhost"  # broker ip
PORT = 1883  # broker port
TOPIC = "smartwaste/sensor/reading-data"

# Sample data representing three sensors with distances
MESSAGE = [
    {
        "macAddress": "34:b7:da:5d:80:0c",
        "trigerPin": 7,
        "echoPin": 8,
        "distance": 76.70
    },
    {
        "macAddress": "34:b7:da:5d:80:0c",
        "trigerPin": 12,
        "echoPin": 4,
        "distance": 93.50
    },
    {
        "macAddress": "34:b7:da:5d:80:0c",
        "trigerPin": 2,
        "echoPin": 13,
        "distance": 49.18
    }
]

def publish_readings():
    client = mqtt.Client()
    # Connect to the broker
    client.connect(BROKER, PORT, 60)
    
    # Generate random distances for each sensor
    for sensor in MESSAGE:
        sensor["distance"] = round(random.uniform(20.0, 100.0), 2)
    
    # Convert to JSON and publish
    json_payload = json.dumps(MESSAGE)
    client.publish(TOPIC, json_payload)
    print(f"Published readings to topic '{TOPIC}':")
    print(json_payload)
    
    client.disconnect()

if __name__ == "__main__":
    publish_readings()