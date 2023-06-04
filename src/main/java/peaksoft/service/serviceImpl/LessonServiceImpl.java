package peaksoft.service.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.exception.MyException;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    @Override
    public LessonResponse saveLesson(Long courseId,LessonRequest lessonRequest) throws MyException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new MyException("Course with id:" + courseId + "not found"));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setLessonLink(lessonRequest.getLessonLink());
        course.getLessons().add(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return new LessonResponse(
                lesson.getId(),
                lesson.getLessonName(),
                lesson.getLessonLink()
        );
    }
    @Override
    public List<LessonResponse> getAllLesson() {
        return lessonRepository.getAll();
    }

    @Override
    public LessonResponse getByIdLesson(Long id) throws MyException {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new MyException("Course with id:" + id + "not found"));
        return new LessonResponse(
                lesson.getId(),
                lesson.getLessonName(),
                lesson.getLessonLink()
        );
    }

    @Override
    public LessonResponse updateLessonById(Long id, LessonRequest lessonRequest) throws MyException {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new MyException("Course with id:" + id + "not found"));
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setLessonLink(lessonRequest.getLessonLink());
        lessonRepository.save(lesson);
        return new LessonResponse(
                lesson.getId(),
                lesson.getLessonName(),
                lesson.getLessonLink()
        );
    }

    @Override
    public SimpleResponse deleteById(Long id) throws MyException {
        lessonRepository.findById(id)
                .orElseThrow(() -> new MyException("Instructor with id:" + id + " not found"));
        lessonRepository.deleteById(id);
        return   SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("successfully deleted!")
                .build();
    }
    }

