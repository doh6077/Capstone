package ca.sheridancollege.smartwaste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.smartwaste.beans.Sensor;
import ca.sheridancollege.smartwaste.beans.SensorReadingHistory;

public interface SensorReadingHistoryRepository extends JpaRepository<SensorReadingHistory, Long> {
    List<SensorReadingHistory> findBySensor(Sensor sensor);
    List<SensorReadingHistory> findBySensorOrderByTimestampDesc(Sensor sensor);
}