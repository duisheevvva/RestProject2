package peaksoft.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.entity.Instructor;
import java.util.List;

@Repository
public interface   InstructorRepository  extends JpaRepository<Instructor,Long> {

    @Query("select new peaksoft.dto.response.InstructorResponse(i.id,i.firstName,i.lastName,i.phoneNumber,i.specialization) from Instructor i")
    List<InstructorResponse> getAll();


    @Query("select (count(s)) from Instructor i join i.companies c join c.course co join co.groups g join g.students s where i.id =:instructorId")
    int getAllCount(@Param("instructorId") Long instructorId);



}
