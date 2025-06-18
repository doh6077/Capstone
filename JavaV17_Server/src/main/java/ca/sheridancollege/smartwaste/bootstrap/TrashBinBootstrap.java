package ca.sheridancollege.smartwaste.bootstrap;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.smartwaste.beans.*;
import ca.sheridancollege.smartwaste.services.*;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TrashBinBootstrap implements CommandLineRunner {

    private TrashBinService trashBinService;
    private SensorService sensorService;
    private TrashBinLocationService locationService;

    @Override
    public void run(String... args) throws Exception {

        List<Sensor> sensors = sensorService.findAll();
        List<TrashBinLocation> locations = locationService.findAll();

        if (sensors.size() >= 3 && locations.size() >= 1) {
            TrashBin bin1 = TrashBin.builder()
                .sensor(sensors.get(0))
                .type(TrashBinType.RECYCLABLE)
                .location(locations.get(0))
                .height(120f)
                .name("Bin A")
                .createdDate(LocalDate.now())
                .build();

            TrashBin bin2 = TrashBin.builder()
                .sensor(sensors.get(1))
                .type(TrashBinType.RECYCLABLE)
                .location(locations.get(0))
                .height(100f)
                .name("Bin B")
                .createdDate(LocalDate.now())
                .build();


            trashBinService.save(bin1);
            trashBinService.save(bin2);
        } else {
            System.out.println("🚫 Not enough sensors/types/locations to initialize trash bins.");
        }
    }
}
