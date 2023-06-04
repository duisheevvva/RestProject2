package peaksoft.dto.response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonResponse {
    private Long id;
    private String lessonName;
    private String lessonLink;

    public LessonResponse(Long id, String lessonName, String lessonLink) {
        this.id = id;
        this.lessonName = lessonName;
        this.lessonLink = lessonLink;
    }
}
