package peaksoft.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(generator = "lesson_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "lesson_gen",sequenceName = "lesson_seq",allocationSize = 1)
    private Long id;
    @NotEmpty(message = "fill in the field")
    private String lessonName;
    @NotEmpty(message = "fill in the field")
    private String lessonLink;


    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private Course course;



    @OneToMany(mappedBy = "lesson",cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Task>tasks;


    public Lesson(String lessonName, String lessonLink) {
        this.lessonName = lessonName;
        this.lessonLink = lessonLink;
    }
}
