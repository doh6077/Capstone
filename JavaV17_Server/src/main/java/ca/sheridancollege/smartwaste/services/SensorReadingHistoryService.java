package ca.sheridancollege.smartwaste.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.sheridancollege.smartwaste.beans.Sensor;
import ca.sheridancollege.smartwaste.beans.SensorReadingHistory;

@Service
public interface SensorReadingHistoryService {
    
    public List<SensorReadingHistory> findAll();
    public SensorReadingHistory findById(Long id);
    public List<SensorReadingHistory> findBySensor(Sensor sensor);
    public SensorReadingHistory save(SensorReadingHistory sensorReadingHistory);
    public void delete(Long id);
    public List<SensorReadingHistory> findBySensorOrderByTimestampDesc(Sensor sensor);
}