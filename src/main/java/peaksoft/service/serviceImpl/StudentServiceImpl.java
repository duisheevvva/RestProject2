package peaksoft.service.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.enums.StudyFormat;
import peaksoft.exception.MyException;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(StudyFormat.valueOf(studentRequest.getStudyFormat()));
        studentRepository.save(student);
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getIsBlocked()
        );
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        return studentRepository.getAll();
    }

    @Override
    public StudentResponse getByIdStudent(Long id) throws MyException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new MyException("Student with id:" + id + "not found"));
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getIsBlocked()
        );
    }

    @Override
    public StudentResponse updateStudentById(Long id, StudentRequest studentRequest) throws MyException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new MyException("Student with id:" + id + "not found"));
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(StudyFormat.valueOf(studentRequest.getStudyFormat()));
        studentRepository.save(student);
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getIsBlocked()

        );
    }

    @Override
    public SimpleResponse deleteById(Long id) throws MyException {
        studentRepository.findById(id)
                .orElseThrow(() -> new MyException("Instructor with id:" + id + " not found"));
        studentRepository.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("successfully deleted!")
                .build();
    }

    @Override
    public SimpleResponse assignStudentToGroup(Long id, Long groupId) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Student with id: " + id + " is not found"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NullPointerException("Group with id: " + groupId + " is not found"));

        student.setGroup(group);
        group.getStudents().add(student);
        studentRepository.save(student);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("successfully assigned!")
                .build();
    }

    @Override
    public SimpleResponse blockUnblockStudent(Long studentId, Boolean block) {
        Student student1 = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student with id:" + studentId + "is not found"));
        student1.setIsBlocked(block);
        studentRepository.save(student1);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("Student with id:" + studentId + " is blocked")
                .build();
    }

    @Override
    public List<StudentResponse> getFilter(String studyFormat) {
        Student student=new Student();
        if(studyFormat.equals("ONLINE")) {
            return studentRepository.getFilterOnLine();
        } else if (studyFormat.equals("OFFLINE")) {
            return studentRepository.getFilterOffLine();
        }
        return Collections.singletonList(StudentResponse.builder().
                id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .isBlocked(student.getIsBlocked())
                .studyFormat(student.getStudyFormat())
                .build());

    }

}



