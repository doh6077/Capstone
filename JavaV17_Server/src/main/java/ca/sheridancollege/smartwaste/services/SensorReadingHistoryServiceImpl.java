package ca.sheridancollege.smartwaste.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.sheridancollege.smartwaste.beans.Sensor;
import ca.sheridancollege.smartwaste.beans.SensorReadingHistory;
import ca.sheridancollege.smartwaste.repositories.SensorReadingHistoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SensorReadingHistoryServiceImpl implements SensorReadingHistoryService {

    private SensorReadingHistoryRepository sensorReadingHistoryRepository;

    @Override
    public List<SensorReadingHistory> findAll() {
        return sensorReadingHistoryRepository.findAll();
    }

    @Override
    public SensorReadingHistory findById(Long id) {
        return sensorReadingHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<SensorReadingHistory> findBySensor(Sensor sensor) {
        return sensorReadingHistoryRepository.findBySensor(sensor);
    }

    @Override
    public SensorReadingHistory save(SensorReadingHistory sensorReadingHistory) {
        return sensorReadingHistoryRepository.save(sensorReadingHistory);
    }

    @Override
    public void delete(Long id) {
        sensorReadingHistoryRepository.deleteById(id);
    }

    @Override
    public List<SensorReadingHistory> findBySensorOrderByTimestampDesc(Sensor sensor) {
        return sensorReadingHistoryRepository.findBySensorOrderByTimestampDesc(sensor);
    }
}