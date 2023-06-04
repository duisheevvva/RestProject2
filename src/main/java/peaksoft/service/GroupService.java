package peaksoft.service;

import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;

import java.util.List;

public interface GroupService {
    GroupResponse saveGroup(Long courseId , GroupRequest groupRequest) throws MyException;

    List<GroupResponse> getAllGroup();

    GroupResponse getByIdGroup(Long id) throws MyException;

    GroupResponse updateGroupById(Long id,GroupRequest groupRequest) throws MyException;

    SimpleResponse deleteById(Long id) throws MyException;


}
