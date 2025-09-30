package ca.sheridancollege.smartwaste.beans;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cleaner {

    // Primary key for the Cleaner table, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic profile information for the cleaner
    private String name;
    private String email;
    private String phoneNumber;

    // Many-to-many relationship with TrashBin
    // A cleaner can be assigned to multiple bins and vice versa
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "cleaner_trash_bin", // Name of the join table
        joinColumns = @JoinColumn(name = "cleaner_id"), // FK referencing Cleaner
        inverseJoinColumns = @JoinColumn(name = "bin_id") // FK referencing TrashBin
    )
    @JsonIgnore 
    private List<TrashBin> bins;

    // Many-to-many relationship with Shift
    // A cleaner can have multiple shifts and each shift can include multiple cleaners
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "shift_cleaner", // Name of the join table
        joinColumns = @JoinColumn(name = "cleaner_id"), // FK referencing Cleaner
        inverseJoinColumns = @JoinColumn(name = "shift_id") // FK referencing Shift
    )
    @JsonIgnore 
    private List<Shift> shifts;

    // Transient field used for receiving shift IDs from the frontend
    // Not stored in the database
    @Transient
    private List<Long> shiftIds;
}
