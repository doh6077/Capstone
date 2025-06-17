package ca.sheridancollege.smartwaste.services;

import java.util.List;

import ca.sheridancollege.smartwaste.beans.TrashBinType;

public interface TrashBinTypeService {
    List<TrashBinType> findAll();
    TrashBinType findById(Long id);
    TrashBinType findByType(String type);
    TrashBinType save(TrashBinType trashBinType);
    TrashBinType update(Long id, TrashBinType updatedType);
    void delete(Long id);
}
