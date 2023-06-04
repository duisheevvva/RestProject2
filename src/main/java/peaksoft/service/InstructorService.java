package peaksoft.service;

import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.StudentCountResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;

import java.util.List;

public interface InstructorService {

    InstructorResponse saveInstructor( InstructorRequest instructorRequest);

    List<InstructorResponse> getAllInstructor();

    InstructorResponse getByIdInstructor(Long id) throws MyException;

    InstructorResponse updateInstructorById(Long id, InstructorRequest instructorRequest) throws MyException;

    SimpleResponse deleteById(Long id) throws MyException;

    SimpleResponse assignInstructorToCompany(Long id,Long companyId);

    StudentCountResponse getCountStudent(Long id);

}
