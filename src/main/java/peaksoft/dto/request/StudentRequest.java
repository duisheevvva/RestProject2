package peaksoft.dto.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.StudyFormat;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @NotEmpty(message = "fill in the field")
    private String firstName;
    @NotEmpty(message = "fill in the field")
    private String lastName;
    @NotEmpty(message = "fill in the field")
    @Pattern(regexp = "\\+996\\d{9}",message = "wrong format")
    private String phoneNumber;
    @NotEmpty(message = "fill in the field")
    private String email;
    private String studyFormat;
    private Long groupId;
}
