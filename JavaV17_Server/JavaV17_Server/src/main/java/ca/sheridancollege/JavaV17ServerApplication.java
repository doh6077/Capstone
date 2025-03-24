package ca.sheridancollege;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaV17ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaV17ServerApplication.class, args);
		String broker = "tcp://10.14.3.8:1883";
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
