package ca.sheridancollege.smartwaste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.smartwaste.beans.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
