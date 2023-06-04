package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(generator = "course_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_gen",sequenceName = "course_seq",allocationSize = 1)
    private Long id;
    @NotEmpty(message = "fill in the field")
    private String courseName;
//    @NotEmpty(message = "fill in the field")
    private LocalDate dateOfStart;
    @NotEmpty(message = "fill in the field")
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Company company;



    @ManyToMany(mappedBy = "courses",cascade = {CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    private List<Group> groups;



    @OneToMany(mappedBy = "course",cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Lesson>lessons;





    public Course(String courseName, LocalDate dateOfStart, String description) {
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }
}
