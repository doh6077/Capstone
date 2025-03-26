package ca.sheridancollege.smartwaste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.smartwaste.beans.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
	boolean existsByMacAddressAndTrigerPinAndEchoPin(String macAddress, int trigerPin, int echoPin);
}
