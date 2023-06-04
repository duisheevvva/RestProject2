package peaksoft.dto.response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponse {
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadline;


    public TaskResponse(Long id, String taskName, String taskText, LocalDate deadline) {
        this.id = id;
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;

    }
}
