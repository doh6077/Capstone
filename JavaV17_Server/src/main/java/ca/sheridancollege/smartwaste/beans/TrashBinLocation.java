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
public class TrashBinLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long geoID;

    private String address;
}
