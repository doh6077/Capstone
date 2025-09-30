package ca.sheridancollege.smartwaste.beans;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shift {
     // Primary key for Shift table, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Enum representing the day of the week (e.g., MONDAY, TUESDAY, etc.)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    // Optional: Start and end time fields kept for potential future use
    //private String startTime;
    //private String endTime;

    // Enum representing the predefined time slot (e.g., MORNING, AFTERNOON)
    @Enumerated(EnumType.STRING)
    private ShiftTime shiftTime;

    // Many-to-Many relationship: a shift can have multiple cleaners
    // Mapped by the "shifts" field in the Cleaner entity
    @ManyToMany(mappedBy = "shifts", fetch = FetchType.LAZY)
    private List<Cleaner> cleaners;

    // Transient field used for receiving cleaner IDs from the frontend
    // This is not persisted to the database
    @Transient
    private List<Long> cleanerIds;
}
