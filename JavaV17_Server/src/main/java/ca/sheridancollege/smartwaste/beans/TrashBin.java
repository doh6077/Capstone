package ca.sheridancollege.smartwaste.beans;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrashBin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long binID;

    // Many-to-many with Cleaner (inverse side)
    @ManyToMany(mappedBy = "bins")
    private List<Cleaner> cleaners;

    // One sensor per bin
    @OneToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    // Many bins can share the same type
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TrashBinType type;

    // Many bins can be in the same location
    @ManyToOne
    @JoinColumn(name = "geo_id")
    private TrashBinLocation location;

    private float height;
    private String name;

    private LocalDate createdDate;
}
