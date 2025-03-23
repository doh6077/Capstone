package ca.sheridancollege.kimdohee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.kimdohee.beans.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
