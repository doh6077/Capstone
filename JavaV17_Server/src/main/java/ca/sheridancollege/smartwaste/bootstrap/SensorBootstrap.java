package ca.sheridancollege.smartwaste.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.smartwaste.beans.Sensor;
import ca.sheridancollege.smartwaste.services.SensorService;
import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class SensorBootstrap implements CommandLineRunner {
	
	private SensorService sensorService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		// save dummy data 
		//sensorService.save(Sensor.builder().batteryStatus("High").location("A building").lastBatteryUpdateDate("Mar 23").build());

	}

}
