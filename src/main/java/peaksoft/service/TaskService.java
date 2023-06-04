package peaksoft.service;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.TaskResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;

import java.util.List;

public interface TaskService {
    TaskResponse saveTask(Long lessonId, TaskRequest taskRequest) throws MyException;

    List<TaskResponse> getAllTask();

    TaskResponse getByIdTask(Long id) throws MyException;

    TaskResponse updateTaskById(Long id, TaskRequest taskRequest) throws MyException;

    SimpleResponse deleteById(Long id) throws MyException;
}
