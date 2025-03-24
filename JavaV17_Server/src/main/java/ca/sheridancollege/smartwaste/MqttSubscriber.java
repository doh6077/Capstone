package ca.sheridancollege.smartwaste;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscriber {
	public static void main(String[] args) {
        //String broker = "tcp://10.23.169.55:1883"; 
        String broker = "tcp://10.14.3.8"; 
        String topic = "test/topic";              
        String clientId = "JavaSubscriber";
 
        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
 
            System.out.println("Connecting to broker: " + broker);
            client.connect(options);
            System.out.println("Connected");
 
            client.subscribe(topic);
            System.out.println("Subscribed to topic: " + topic);
 
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost: " + cause.getMessage());
                }
 
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Message received:");
                    System.out.println("\tTopic: " + topic);
                    System.out.println("\tMessage: " + new String(message.getPayload()));
                }
 
                @Override
                public void deliveryComplete(org.eclipse.paho.client.mqttv3.IMqttDeliveryToken token) {
                    // Not used for subscriber
                }
            });
 
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
