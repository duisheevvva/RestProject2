package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(generator = "task_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_gen",sequenceName = "task_seq",allocationSize = 1)
    private Long id;
    @NotEmpty(message = "fill in the field")
    private String taskName;
    @NotEmpty(message = "fill in the field")
    private String taskText;
//    @NotEmpty(message = "fill in the field")
    private LocalDate deadline;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Lesson lesson;

    public Task(String taskName, String taskText, LocalDate deadline) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
    }
}
