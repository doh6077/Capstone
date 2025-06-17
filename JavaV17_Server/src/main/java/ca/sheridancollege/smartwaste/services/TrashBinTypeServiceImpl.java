package ca.sheridancollege.smartwaste.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.sheridancollege.smartwaste.beans.TrashBinType;
import ca.sheridancollege.smartwaste.repositories.TrashBinTypeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrashBinTypeServiceImpl implements TrashBinTypeService {

    private TrashBinTypeRepository trashBinTypeRepository;

    @Override
    public List<TrashBinType> findAll() {
        return trashBinTypeRepository.findAll();
    }

    @Override
    public TrashBinType findById(Long id) {
        return trashBinTypeRepository.findById(id).orElse(null);
    }

    @Override
    public TrashBinType findByType(String type) {
        return trashBinTypeRepository.findByType(type).orElse(null);
    }

    @Override
    public TrashBinType save(TrashBinType trashBinType) {
        return trashBinTypeRepository.save(trashBinType);
    }

    @Override
    public TrashBinType update(Long id, TrashBinType updatedType) {
        return trashBinTypeRepository.findById(id).map(existing -> {
            existing.setType(updatedType.getType());
            return trashBinTypeRepository.save(existing);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        trashBinTypeRepository.deleteById(id);
    }
}
