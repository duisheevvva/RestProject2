package peaksoft.service;

import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;

import java.util.List;

public interface CourseService {
    CourseResponse saveCourse(Long companyId ,CourseRequest courseRequest) throws MyException;

    List<CourseResponse> getAllCourse(Long companyId,String ascOrDesc);

   CourseResponse getByIdCourse(Long id) throws MyException;

    CourseResponse updateCourseById(Long id,CourseRequest courseRequest) throws MyException;

    SimpleResponse deleteById(Long id) throws MyException;


}
