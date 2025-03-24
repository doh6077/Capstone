package ca.sheridancollege.smartwaste.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.sheridancollege.smartwaste.beans.Sensor;
import ca.sheridancollege.smartwaste.repositories.SensorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class SensorServiceImpl implements SensorService {
	
	
	private SensorRepository sensorRepository;

	
	@Override
	public List<Sensor> findAll() {
		// TODO Auto-generated method stub
		return sensorRepository.findAll();
	}

	@Override
	public Sensor findById(Long id) {
		if (sensorRepository.findById(id).isPresent())
			return sensorRepository.findById(id).get();
		else
			return null; 
	}



	@Override
	public Sensor save(Sensor sensor) {
		// TODO Auto-generated method stub
		return sensorRepository.save(sensor);
	}

}
