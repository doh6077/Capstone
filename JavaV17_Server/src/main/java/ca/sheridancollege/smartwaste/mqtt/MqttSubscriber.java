package ca.sheridancollege.smartwaste.mqtt;

import java.time.LocalDateTime;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.sheridancollege.smartwaste.beans.Sensor;
import ca.sheridancollege.smartwaste.beans.SensorReadingHistory;
import ca.sheridancollege.smartwaste.repositories.SensorRepository;
import ca.sheridancollege.smartwaste.services.SensorReadingHistoryService;
import ca.sheridancollege.smartwaste.services.SensorService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class MqttSubscriber {
	
	@Autowired
	private SensorService sensorService; 
	
	@Autowired
	private SensorReadingHistoryService sensorReadingHistoryService;
	
	@Autowired
	private SensorRepository sensorRepository;

	
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
					// Console outpyt: Received Message from smartwaste/sensor/metadata: {"macAddress": "34:b7:da:5d:80:0c", "trigerPin1": 7, "echoPin1": 8, "trigerPin2": 12, "echoPin2": 4, "trigerPin3": 2, "echoPin3": 13}
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
				processReadingData(new String(message.getPayload()));
				break;
			default:
				System.out.println("Unknown topic: " + topic);
			}
		} catch (Exception e) {
			System.err.println("Error processing message: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void processReadingData(String payload) {
		try {
			// Check if payload is an array
			if (payload.trim().startsWith("[")) {
				// Parse as JSON array
				JSONArray readingsArray = new JSONArray(payload);
				
				for (int i = 0; i < readingsArray.length(); i++) {
					JSONObject reading = readingsArray.getJSONObject(i);
					processSingleReading(reading);
				}
			} else {
				// Try to parse as single JSON object
				JSONObject reading = new JSONObject(payload);
				processSingleReading(reading);
			}
		} catch (Exception e) {
			System.err.println("Error processing reading data: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void processSingleReading(JSONObject reading) {
		try {
			String macAddress = reading.getString("macAddress");
			int trigerPin = reading.getInt("trigerPin1");
			int echoPin = reading.getInt("echoPin1");
			double distance = reading.getDouble("distance");
			
			// Find the corresponding sensor
			Sensor sensor = sensorRepository.findByMacAddressAndTrigerPinAndEchoPin(macAddress, trigerPin, echoPin)
					.orElse(null);
			
			if (sensor != null) {
				// Create and save the reading history
				SensorReadingHistory readingHistory = SensorReadingHistory.builder()
						.sensor(sensor)
						.distanceReading(distance)
						.timestamp(LocalDateTime.now())
						.build();
				
				sensorReadingHistoryService.save(readingHistory);
				System.out.println("Saved reading history: " + readingHistory);
			} else {
				System.out.println("Sensor not found for MAC: " + macAddress + 
						", trigerPin: " + trigerPin + ", echoPin: " + echoPin);
			}
		} catch (Exception e) {
			System.err.println("Error processing single reading: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
