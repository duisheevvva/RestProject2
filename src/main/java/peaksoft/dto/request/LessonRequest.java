package peaksoft.dto.request;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonRequest {
    @NotEmpty(message = "fill in the field")
    private String lessonName;
    @NotEmpty(message = "fill in the field")
    private String lessonLink;
    private Long courseId;
}
