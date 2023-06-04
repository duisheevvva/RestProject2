package peaksoft.api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;
import peaksoft.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    @GetMapping("/getAll")
    public List<StudentResponse> getAllStudent() {
        return studentService.getAllStudent();
    }


    @PostMapping("/save")
    public StudentResponse saveStudent( @Valid @RequestBody StudentRequest studentRequest) throws MyException {
        return studentService.saveStudent(studentRequest);
    }


    @GetMapping("/getById/{id}")
    public StudentResponse getStudentById(@PathVariable("id") Long id) throws MyException {
        return studentService.getByIdStudent(id);
    }

    @PostMapping("/update/{id}")
    public StudentResponse updateStudent( @Valid @PathVariable("id") Long id,
                                       @RequestBody StudentRequest studentRequest) throws MyException {
        return studentService.updateStudentById(id,studentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteInstructor(@PathVariable("id") Long id) throws MyException {
        return studentService.deleteById(id);
    }

    @PostMapping("/assign/{id}/{groupId}")
    public SimpleResponse assignStudentToGroup(@PathVariable Long id, @PathVariable Long groupId){
        return studentService.assignStudentToGroup(id,groupId);
    }

    @PostMapping("/blocked/{studentId}")
    public SimpleResponse blockUnblockStudent(@PathVariable Long studentId, @RequestParam Boolean block){
        return studentService.blockUnblockStudent(studentId,block);
    }

    @GetMapping("/filter")
    public List<StudentResponse> getFilterStudent(@RequestParam String studyFormat){
        return studentService.getFilter(studyFormat);
    }
}
