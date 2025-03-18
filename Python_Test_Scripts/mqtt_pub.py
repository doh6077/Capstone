import paho.mqtt.client as mqtt

BROKER = "localhost"  # broker ip
PORT = 1883  # broker port
TOPIC = "test/topic"
MESSAGE = "Hello, MQTT!"

client = mqtt.Client()
# Connect to the broker
client.connect(BROKER, PORT, 60)
# Publish a message
client.publish(TOPIC, MESSAGE)
print(f"Published '{MESSAGE}' to topic '{TOPIC}'")

client.disconnect()