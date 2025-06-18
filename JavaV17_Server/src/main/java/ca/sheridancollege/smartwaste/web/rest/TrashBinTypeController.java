package ca.sheridancollege.smartwaste.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import ca.sheridancollege.smartwaste.beans.TrashBinType;
import ca.sheridancollege.smartwaste.services.TrashBinTypeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/trashbintypes")
@AllArgsConstructor
public class TrashBinTypeController {

    private TrashBinTypeService trashBinTypeService;

    @GetMapping({"", "/"})
    public List<TrashBinType> getAllTypes() {
        return trashBinTypeService.findAll();
    }

    @GetMapping("/{id}")
    public TrashBinType getTypeById(@PathVariable Long id) {
        return trashBinTypeService.findById(id);
    }

    @PostMapping(value = {"", "/"}, headers = {"Content-type=application/json"})
    public TrashBinType addType(@RequestBody TrashBinType type) {
        type.setTypeID(null);
        return trashBinTypeService.save(type);
    }

    @PutMapping("/{id}")
    public TrashBinType updateType(@PathVariable Long id, @RequestBody TrashBinType updatedType) {
        return trashBinTypeService.update(id, updatedType);
    }

    @DeleteMapping("/{id}")
    public void deleteType(@PathVariable Long id) {
        trashBinTypeService.delete(id);
    }
}
