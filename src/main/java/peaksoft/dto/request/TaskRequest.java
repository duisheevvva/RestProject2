package peaksoft.dto.request;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    @NotEmpty(message = "fill in the field")
    private String taskName;
    @NotEmpty(message = "fill in the field")
    private String taskText;
//    @NotEmpty(message = "fill in the field")
    private LocalDate deadline;
    private Long lessonId;
}
