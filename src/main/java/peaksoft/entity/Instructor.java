package peaksoft.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(generator = "instructor_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "instructor_gen",sequenceName = "instructor_seq",allocationSize = 1)
    private Long id;
    @NotEmpty(message = "fill in the field")
    private String firstName;
    @NotEmpty(message = "fill in the field")
    private String lastName;
    @NotEmpty(message = "fill in the field")
    @Pattern(regexp = "\\+996\\d{9}",message = "wrong format")
    private String phoneNumber;
    @NotEmpty(message = "fill in the field")
    private String specialization;



    @ManyToMany(mappedBy = "instructors",cascade = {CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private List<Company>companies;


    public Instructor(String firstName, String lastName, String phoneNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
