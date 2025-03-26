import paho.mqtt.client as mqtt

BROKER = "localhost" # broker ip
PORT = 1883 # broker port
TOPIC = "smartwaste/sensor/metadata"

# Callback function for when a message is received
def on_message(client, userdata, msg):
    print(f"Received message: '{msg.payload.decode()}' on topic '{msg.topic}'")

client = mqtt.Client()

# Assign the on_message callback
client.on_message = on_message

# Connect to the broker
client.connect(BROKER, PORT, 60)

# Subscribe to the topic
client.subscribe(TOPIC)
print(f"Subscribed to topic '{TOPIC}'")

# Keep listening for messages
client.loop_forever()