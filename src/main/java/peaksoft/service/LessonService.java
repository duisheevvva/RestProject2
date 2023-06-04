package peaksoft.service;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;
import java.util.List;



public interface LessonService {
    LessonResponse saveLesson(Long courseId,LessonRequest lessonRequest) throws MyException;

    List<LessonResponse> getAllLesson();

    LessonResponse getByIdLesson(Long id) throws MyException;

    LessonResponse updateLessonById(Long id, LessonRequest lessonRequest) throws MyException;

    SimpleResponse deleteById(Long id) throws MyException;
}
