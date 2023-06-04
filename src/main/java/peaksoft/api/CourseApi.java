package peaksoft.api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;
import peaksoft.service.CourseService;
import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    @GetMapping("/getAll/{companyId}")
    public List<CourseResponse> getAllCourses( @PathVariable  Long companyId, @RequestParam String ascOrDesc) {
        return courseService.getAllCourse(companyId,ascOrDesc);
    }

    @PostMapping("/save")
    public CourseResponse saveCourses(@Valid @RequestBody CourseRequest courseRequest) throws MyException {
        return courseService.saveCourse(courseRequest.getCompanyId(), courseRequest);
    }

    @GetMapping("/getById/{id}")
    public CourseResponse getCourseById(@PathVariable("id") Long id) throws MyException {
        return courseService.getByIdCourse(id);
    }

    @PostMapping("/update/{id}")
    public CourseResponse updateCourse(@PathVariable("id") Long id,
                                       @Valid @RequestBody CourseRequest courseRequest) throws MyException {
        return courseService.updateCourseById(id, courseRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteCourse(@PathVariable("id") Long id) throws MyException {
        return courseService.deleteById(id);
    }
}
