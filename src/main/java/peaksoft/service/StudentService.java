package peaksoft.service;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;
import java.util.List;

public interface StudentService {
    StudentResponse saveStudent(StudentRequest studentRequest) throws MyException;

    List<StudentResponse> getAllStudent();

    StudentResponse getByIdStudent(Long id) throws MyException;

    StudentResponse updateStudentById(Long id, StudentRequest studentRequest) throws MyException;

    SimpleResponse deleteById(Long id) throws MyException;

    SimpleResponse assignStudentToGroup(Long id,Long groupId);
    SimpleResponse blockUnblockStudent(Long studentId, Boolean block);

    List<StudentResponse> getFilter(String  studyFormat);
}
