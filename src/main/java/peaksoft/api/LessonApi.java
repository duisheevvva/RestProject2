package peaksoft.api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;
import peaksoft.service.LessonService;
import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;

    @GetMapping("/getAll")
    public List<LessonResponse> getAllLesson() {
        return lessonService.getAllLesson();
    }

    @PostMapping("/save")
    public LessonResponse saveLesson(@Valid @RequestBody LessonRequest lessonRequest) throws MyException {
        return lessonService.saveLesson(lessonRequest.getCourseId(), lessonRequest);
    }

    @GetMapping("/getById/{id}")
    public LessonResponse getLessonById(@PathVariable("id") Long id) throws MyException {
        return lessonService.getByIdLesson(id);
    }

    @PostMapping("/update/{id}")
    public LessonResponse updateLesson(@Valid @PathVariable("id") Long id,
                                       @RequestBody LessonRequest lessonRequest) throws MyException {
        return lessonService.updateLessonById(id,lessonRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteInstructor(@PathVariable("id") Long id) throws MyException {
        return lessonService.deleteById(id);
    }
}
