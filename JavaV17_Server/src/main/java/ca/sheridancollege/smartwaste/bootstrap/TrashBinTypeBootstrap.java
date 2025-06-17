package ca.sheridancollege.smartwaste.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.smartwaste.beans.TrashBinType;
import ca.sheridancollege.smartwaste.services.TrashBinTypeService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TrashBinTypeBootstrap implements CommandLineRunner {

    private TrashBinTypeService trashBinTypeService;

    @Override
    public void run(String... args) throws Exception {
        if (trashBinTypeService.findAll().isEmpty()) {
            trashBinTypeService.save(TrashBinType.builder().type("Recycling").build());
            trashBinTypeService.save(TrashBinType.builder().type("Compost").build());
            trashBinTypeService.save(TrashBinType.builder().type("Garbage").build());
        }
    }
}
