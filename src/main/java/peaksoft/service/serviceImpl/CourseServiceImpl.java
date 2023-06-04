package peaksoft.service.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.exception.MyException;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CourseService;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    @Override
    public CourseResponse saveCourse(Long companyId, CourseRequest courseRequest) throws MyException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new MyException("Company with id: " + companyId + "not found"));
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(LocalDate.now());
        course.setDescription(courseRequest.getDescription());
        company.getCourse().add(course);
        course.setCompany(company);
        courseRepository.save(course);
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDateOfStart(),
                course.getDescription()
        );
    }


    @Override
    public List<CourseResponse> getAllCourse(Long companyId,String ascOrDesc) {
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            return courseRepository.getAllSortedCourseAsc(companyId);
        } else if (ascOrDesc.equalsIgnoreCase("desc")) {
            return courseRepository.getAllSortedCourseDesc(companyId);
        } else {
            throw new NullPointerException("is empty");
        }
    }


    @Override
    public CourseResponse getByIdCourse(Long id) throws MyException {
        Course course = courseRepository.findById(id).
                orElseThrow(() -> new MyException("Course with id:" + id + "is not found"));
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDateOfStart(),
                course.getDescription()
        );
    }

    @Override
    public CourseResponse updateCourseById(Long id, CourseRequest courseRequest) throws MyException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new MyException("Course with id:" + id + "is not found"));
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(LocalDate.now());
        course.setDescription(courseRequest.getDescription());
        courseRepository.save(course);
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDateOfStart(),
                course.getDescription()
        );
    }

    @Override
    public SimpleResponse deleteById(Long id) throws MyException {
        courseRepository.findById(id).orElseThrow(() -> new MyException("Course with id:" + id + "is not found"));
        courseRepository.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("successfully deleted!")
                .build();
    }
}

