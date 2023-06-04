package peaksoft.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.StudyFormat;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "student_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen",sequenceName = "student_seq",allocationSize = 1)
    private Long id;
    @NotEmpty(message = "fill in the field")
    private String firstName;
    @NotEmpty(message = "fill in the field")
    private String lastName;
    @NotEmpty(message = "fill in the field")
    @Pattern(regexp = "\\+996\\d{9}",message = "wrong format")
    private String phoneNumber;
    @NotEmpty(message = "fill in the field")
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private Boolean isBlocked;

    @ManyToOne(cascade ={CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH} )
    private Group group;



    public Student(String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }
}
