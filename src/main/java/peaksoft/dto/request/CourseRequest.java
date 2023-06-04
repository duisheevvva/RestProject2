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
public class CourseRequest {
    @NotEmpty(message = "fill in the field")
    private String courseName;
    private LocalDate dateOfStart;
    @NotEmpty(message = "fill in the field")
    private String description;
    private Long companyId;
}
