package peaksoft.api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.StudentCountResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;
import peaksoft.service.InstructorService;
import java.util.List;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;

    @GetMapping("/getAll")
    public List<InstructorResponse> getAllInstructor() {
        return instructorService.getAllInstructor();
    }

    @PostMapping("/save")
    public InstructorResponse saveInstructor( @Valid  @RequestBody InstructorRequest instructorRequest) throws MyException {
        return instructorService.saveInstructor(instructorRequest);
    }

    @GetMapping("/getById/{id}")
    public InstructorResponse getInstructorById(@PathVariable("id") Long id) throws MyException {
        return instructorService.getByIdInstructor(id);
    }

    @PostMapping("/update/{id}")
    public InstructorResponse updateInstructor(@Valid @PathVariable("id") Long id,
                                               @RequestBody InstructorRequest instructorRequest) throws MyException {
        return instructorService.updateInstructorById(id, instructorRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteInstructor(@PathVariable("id") Long id) throws MyException {
        return instructorService.deleteById(id);
    }

    @PostMapping("/assign/{id}/{companyId}")
    public SimpleResponse assignInstructorToCompany(@PathVariable("id")Long id,@PathVariable Long companyId){
        return instructorService.assignInstructorToCompany(id,companyId);
    }

    @GetMapping("/count/{id}")
    public StudentCountResponse getCount(@PathVariable Long id){
        return instructorService.getCountStudent(id);
    }

}
