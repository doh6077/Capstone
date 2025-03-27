package ca.sheridancollege.smartwaste.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.sheridancollege.smartwaste.beans.Sensor;
import ca.sheridancollege.smartwaste.services.SensorService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class MqttSubscriber {
	
	@Autowired
	private SensorService sensorService; 
	//GPT:
	//private final SensorService sensorService;

	
	private static final String BROKER_URL = "tcp://localhost:1883"; // Public broker or your own
	private static final String CLIENT_ID = "SpringBootSubscriber";
	private static final String[] TOPICS = { "smartwaste/sensor/metadata", "smartwaste/sensor/reading-data" };
	//private MqttClient client;

//	@PostConstruct
//    public void init() {
	public MqttSubscriber() {
		try {
			//client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());
			 MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(true);

			client.setCallback(new MqttCallback() {
				@Override
				public void connectionLost(Throwable cause) {
					System.out.println("MQTT Connection Lost!");
				}

				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {
					System.out.println("Received Message from " + topic + ": " + new String(message.getPayload()));
					handleIncomingMessage(topic, message);
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {
					// Not needed for subscribers
				}
			});

			client.connect(options);
			client.subscribe(TOPICS);
			// System.out.println("Subscribed to topics: " + String.join(", ", TOPICS));

		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	// GPT:
	/* @PostConstruct
	public void init() {
	    try {
	        MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());
	        MqttConnectOptions options = new MqttConnectOptions();
	        options.setCleanSession(true);

	        client.setCallback(new MqttCallback() {
	            @Override
	            public void connectionLost(Throwable cause) {
	                System.out.println("MQTT Connection Lost!");
	            }

	            @Override
	            public void messageArrived(String topic, MqttMessage message) throws Exception {
	                System.out.println("Received Message from " + topic + ": " + new String(message.getPayload()));
	                handleIncomingMessage(topic, message);
	            }

	            @Override
	            public void deliveryComplete(IMqttDeliveryToken token) {
	            }
	        });

	        client.connect(options);
	        client.subscribe(TOPICS);
	        System.out.println("MQTT subscriber initialized.");

	    } catch (MqttException e) {
	        e.printStackTrace();
	    }
	}*/


	private void handleIncomingMessage(String topic, MqttMessage message) {
		try {
			switch (topic) {
			case "smartwaste/sensor/metadata":
				JSONObject metaDataJSON = new JSONObject(new String(message.getPayload()));
				Sensor sensor1 = Sensor.builder().macAddress(metaDataJSON.get("macAddress").toString())
						.trigerPin(Integer.parseInt(metaDataJSON.get("trigerPin1").toString()))
						.echoPin(Integer.parseInt(metaDataJSON.get("echoPin1").toString())).build();
				Sensor sensor2 = Sensor.builder().macAddress(metaDataJSON.get("macAddress").toString())
						.trigerPin(Integer.parseInt(metaDataJSON.get("trigerPin2").toString()))
						.echoPin(Integer.parseInt(metaDataJSON.get("echoPin2").toString())).build();
				Sensor sensor3 = Sensor.builder().macAddress(metaDataJSON.get("macAddress").toString())
						.trigerPin(Integer.parseInt(metaDataJSON.get("trigerPin3").toString()))
						.echoPin(Integer.parseInt(metaDataJSON.get("echoPin3").toString())).build();
				sensorService.save(sensor1);
				sensorService.save(sensor2);
				sensorService.save(sensor3);

				break;
			case "smartwaste/sensor/reading-data":
				System.out.println("Handling sensor reading data: " + new String(message.getPayload()));
				break;
			default:
				System.out.println("Unknown topic: " + topic);
			}
		} catch (Exception e) {
			System.err.println("Error processing message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
