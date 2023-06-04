package peaksoft.service.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.StudentCountResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Instructor;
import peaksoft.exception.MyException;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;

    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return new InstructorResponse(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getPhoneNumber(),
                instructor.getSpecialization()

        );
    }

    @Override
    public List<InstructorResponse> getAllInstructor() {
        return instructorRepository.getAll();
    }

    @Override
    public InstructorResponse getByIdInstructor(Long id) throws MyException {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new MyException("Instructor with id:" + id + " not found"));
        return new InstructorResponse(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getPhoneNumber(),
                instructor.getSpecialization()
        );
    }

    @Override
    public InstructorResponse updateInstructorById(Long id, InstructorRequest instructorRequest) throws MyException {
        Instructor instructor1 = instructorRepository.findById(id)
                .orElseThrow(() -> new MyException("Instructor with id:" + id + " not found"));
        instructor1.setFirstName(instructorRequest.getFirstName());
        instructor1.setLastName(instructorRequest.getLastName());
        instructor1.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor1.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor1);
        return new InstructorResponse(
                instructor1.getId(),
                instructor1.getFirstName(),
                instructor1.getLastName(),
                instructor1.getPhoneNumber(),
                instructor1.getSpecialization()

        );
    }

    @Override
    public SimpleResponse deleteById(Long id) throws MyException {
        instructorRepository.findById(id)
                .orElseThrow(() -> new MyException("Instructor with id:" + id + " not found"));
        instructorRepository.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("successfully deleted!")
                .build();
    }

    @Override
    public SimpleResponse assignInstructorToCompany(Long id, Long companyId) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Instructor with id: " + id + " is not found"));
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NullPointerException("Company with id: " + companyId + " is not found"));

        instructor.getCompanies().add(company);
        company.getInstructors().add(instructor);
        instructorRepository.save(instructor);
         return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("successfully assigned!")
                .build();
    }

    @Override
    public StudentCountResponse getCountStudent(Long id) {
        return StudentCountResponse.builder().studentCount(instructorRepository.getAllCount(id)).build();
    }
}

