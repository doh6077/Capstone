package ca.sheridancollege.smartwaste.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.smartwaste.beans.TrashBinType;

public interface TrashBinTypeRepository extends JpaRepository<TrashBinType, Long> {

    // Optional: find by type name (e.g. "Recycling", "Garbage")
    Optional<TrashBinType> findByType(String type);
}
