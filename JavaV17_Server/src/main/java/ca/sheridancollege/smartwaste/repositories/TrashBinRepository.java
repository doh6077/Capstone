package ca.sheridancollege.smartwaste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ca.sheridancollege.smartwaste.beans.TrashBin;

public interface TrashBinRepository extends JpaRepository<TrashBin, Long> {
     // Find bins by name
    Optional<TrashBin> findByName(String name);

    // Find all bins of a certain type
    List<TrashBin> findByType(TrashBinType type);

    // Find all bins at a certain location
    List<TrashBin> findByLocation(TrashBinLocation location);

    // Find all bins assigned to a cleaner (many-to-many)
    List<TrashBin> findByCleaners(Cleaner cleaner);
}
