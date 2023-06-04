package peaksoft.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(generator = "company_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_gen",sequenceName = "company_seq",allocationSize = 1)
    private Long id;
    @NotEmpty(message = "fill in the field")
    @Column(unique = true)
    private String name;
    @NotEmpty(message = "fill in the field")
    private String address;
    @NotEmpty(message = "fill in the field")
    @Pattern(regexp = "\\+996\\d{9}",message = "wrong format")
    private String phoneNumber;


    @OneToMany(mappedBy = "company",cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Course> course;


    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<Instructor>instructors;



    public Company(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
