package peaksoft.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.StudentResponse;
import peaksoft.entity.Student;
import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Long> {
    @Query("select new peaksoft.dto.response.StudentResponse(s.id,s.firstName,s.lastName,s.email,s.phoneNumber,s.studyFormat,s.isBlocked) from Student s")
    List<StudentResponse> getAll();


    @Query("SELECT new peaksoft.dto.response.StudentResponse(s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.studyFormat, s.isBlocked) FROM Student s WHERE s.studyFormat='ONLINE'")
    List<StudentResponse> getFiltrOnLine();

    @Query("SELECT new peaksoft.dto.response.StudentResponse(s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.studyFormat, s.isBlocked) FROM Student s WHERE s.studyFormat='OFFLINE'")
    List<StudentResponse> getFiltrOffLine();
}
