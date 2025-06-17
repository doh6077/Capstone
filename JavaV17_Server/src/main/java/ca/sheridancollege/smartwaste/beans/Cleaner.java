package ca.sheridancollege.smartwaste.beans;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data                
@NoArgsConstructor   
@AllArgsConstructor  
@Builder        
public class Cleaner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

     // Many-to-many with TrashBin
    @ManyToMany
    @JoinTable(
        name = "cleaner_trash_bin", // name of the join table JPA will auto-create
        joinColumns = @JoinColumn(name = "cleaner_id"), // FK to Cleaner
        inverseJoinColumns = @JoinColumn(name = "bin_id") // FK to TrashBin
    )
    private List<TrashBin> bins;
}


