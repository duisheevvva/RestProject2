package peaksoft.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Course;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("select new peaksoft.dto.response.CourseResponse(c.id, c.courseName, c.dateOfStart, c.description) from Course c where c.company.id = :companyId order by c.dateOfStart asc ")
    List<CourseResponse> getAllSortedCourseAsc(Long companyId);
    @Query("select new peaksoft.dto.response.CourseResponse(c.id, c.courseName, c.dateOfStart, c.description) from Course c where c.company.id = :companyId order by c.dateOfStart desc ")
    List<CourseResponse> getAllSortedCourseDesc(Long companyId);
}
